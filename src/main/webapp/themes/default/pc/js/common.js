//取得一个对象，相当于getElementById()
function $$() {
  var elements = new Array();
  for (var i = 0; i < arguments.length; i++) {
    var element = arguments[i];
    if (typeof element == 'string') element = document.getElementById(element);
	Method.Element.apply(element);
    if (arguments.length == 1) return element;
    elements.push(element);
  }
  return elements;
}

//把它接收到的单个的参数转换成一个Array对象。
function $A(list){
	var arr = [];
	for (var i=0,len=list.length; i<len; i++){arr[i] = list[i];}
	return arr;
}

//常用函数扩展
var Method = {
	Element	: function(){
		this.hide = function(){this.style.display="none"; return this;};
		this.show = function(){this.style.display=""; return this;};
		this.getValue = function(){if(this.value===undefined) return this.innerHTML; else return this.value;};
		this.setValue = function(s){if(this.value === undefined) this.setInnerHTML(s); else this.value = s;};
		this.subTag = function(){return $A(this.getElementsByTagName(arguments[0])).each(function(n){$$(n);});};
		this.remove = function(){return this.parentNode.removeChild(this);};
		this.nextElement = function(){var n = this;	for(var i=0,n; n = n.nextSibling; i++) if(n.nodeType==1) return $$(n); return null;};
		this.previousElement = function(){var n = this;	for (var i=0,n; n = n.previousSibling; i++) if(n.nodeType==1) return $$(n); return null;};
		this.getPosition =  function(){var e = this; var t=e.offsetTop; var l=e.offsetLeft; while(e=e.offsetParent){if($$(e).getStyle('position') == 'absolute' || $$(e).getStyle('position') == 'relative') break; t+=e.offsetTop; l+=e.offsetLeft;} return {x:l, y:t};};
		this.getStyle = function(name){ if(this.style[name]) return this.style[name]; else if(this.currentStyle) return this.currentStyle[name]; else if(document.defaultView && document.defaultView.getComputedStyle){ name = name.replace(/([A-Z])/g,"-$1").toLowerCase(); var s = document.defaultView.getComputedStyle(this,""); return s && s.getPropertyValue(name); } else return null;};
		this.setInnerHTML = function(s){var ua = navigator.userAgent.toLowerCase();s = s.replace(/<script([^>]+)src\s*=\s*\"([^>\"\']*)\"([^>]*)>\s*<\/script>/gi,'');if (ua.indexOf('msie') >= 0 && ua.indexOf('opera') < 0){ s = '<div style="display:none">for IE</div>' + s; s = s.replace(/<script([^>]*)>/gi,'<script$1 defer>'); this.innerHTML = '';this.innerHTML = s;	this.removeChild(this.firstChild);}else{var el_next = this.nextSibling; var el_parent = this.parentNode; el_parent.removeChild(this); this.innerHTML = s; if(el_next) el_parent.insertBefore(this, el_next); else el_parent.appendChild(this);}};

		
	},
	Array :	function(){
		this.indexOf = function(){for (i=0; i<this.length; i++) if (this[i]==arguments[0]) return i; return -1;};
		this.each = function(fn){for (var i=0,len=this.length; i<len; i++){	fn(this[i],i);} return this;};
	},
	String : function(){
		this.trim = function(){var _re,_argument = arguments[0] || " ";	typeof(_argument)=="string"?(_argument == " "?_re = /(^\s*)|(\s*$)/g : _re = new RegExp("(^"+_argument+"*)|("+_argument+"*$)","g")) : _re = _argument; return this.replace(_re,"");};
		this.stripTags = function(){return this.replace(/<\/?[^>]+>/gi, '');};
		this.cint = function(){return this.replace(/\D/g,"")*1;};
		this.hasSubString = function(s,f){if(!f) f="";return (f+this+f).indexOf(f+s+f)==-1?false:true;};
	}
};

Method.Array.apply(Array.prototype);
Method.String.apply(String.prototype);

//form相关函数
var Form = {
	//把表格内容转化成string
  serialize: function(form) {
    var elements = Form.getElements($$(form));
    var queryComponents = new Array();
    for (var i = 0; i < elements.length; i++) {
      var queryComponent = Form.Element.serialize(elements[i]);
      if (queryComponent) queryComponents.push(queryComponent);
    }
    return queryComponents.join('&');
  },
  //取得表单内容为数组形式
  getElements: function(form) {
    form = $$(form);
    var elements = new Array();
    for (tagName in Form.Element.Serializers) {
      var tagElements = form.getElementsByTagName(tagName);
      for (var j = 0; j < tagElements.length; j++)
        elements.push(tagElements[j]);
    }
    return elements;
  },
  //disable表单所有内容
  disable: function(form) {
    var elements = Form.getElements(form);
    for (var i = 0; i < elements.length; i++) {
      var element = elements[i];
      element.blur();
      element.disabled = 'true';
    }
  },
  //enable表单所有内容
  enable: function(form) {
    var elements = Form.getElements(form);
    for (var i = 0; i < elements.length; i++) {
      var element = elements[i];
      element.disabled = '';
    }
  },
  //Reset表单
  reset: function(form) {
    $$(form).reset();
  }
}

//form里面元素定义
Form.Element = {
  serialize: function(element) {
    element = $$(element);
    var method = element.tagName.toLowerCase();
    var parameter = Form.Element.Serializers[method](element);
    if (parameter) {
      var key = encodeURIComponent(parameter[0]);
      if (key.length == 0) return;
      if (parameter[1].constructor != Array) return key + '=' + encodeURIComponent(parameter[1]);
	  tmpary = new Array();
	  for (var i = 0; i < parameter[1].length; i++) {
		  tmpary[i] = key + encodeURIComponent('[]') + '=' + encodeURIComponent(parameter[1][i]);
	  }
      return tmpary.join('&');
    }	
  },
  getValue: function(element) {
    element = $$(element);
    var method = element.tagName.toLowerCase();
    var parameter = Form.Element.Serializers[method](element);
    if (parameter) return parameter[1];
  }
}

Form.Element.Serializers = {
  input: function(element) {
    switch (element.type.toLowerCase()) {
      case 'submit':
      case 'hidden':
      case 'password':
      case 'text':
        return Form.Element.Serializers.textarea(element);
      case 'checkbox':
      case 'radio':
        return Form.Element.Serializers.inputSelector(element);
    }
    return false;
  },

  inputSelector: function(element) {
    if (element.checked) return [element.name, element.value];
  },

  textarea: function(element) {
    return [element.name, element.value];
  },

  select: function(element) {
    return Form.Element.Serializers[element.type == 'select-one' ? 'selectOne' : 'selectMany'](element);
  },

  selectOne: function(element) {
    var value = '', opt, index = element.selectedIndex;
    if (index >= 0) {
      opt = element.options[index];
      value = opt.value;
      if (!value && !('value' in opt))
        value = opt.text;
    }
    return [element.name, value];
  },

  selectMany: function(element) {
    var value = new Array();
    for (var i = 0; i < element.length; i++) {
      var opt = element.options[i];
      if (opt.selected) {
        var optValue = opt.value;
        if (!optValue && !('value' in opt))
          optValue = opt.text;
        value.push(optValue);
      }
    }
    return [element.name, value];
  }
}

//取form里面物件的值，等同于Form.Element.getValue()
var $F = Form.Element.getValue;

//常用功能函数
function pageWidth(){
	return window.innerWidth != null ? window.innerWidth : document.documentElement && document.documentElement.clientWidth ? document.documentElement.clientWidth : document.body != null ? document.body.clientWidth : null;
}

function pageHeight(){
	return window.innerHeight != null? window.innerHeight : document.documentElement && document.documentElement.clientHeight ? document.documentElement.clientHeight : document.body != null? document.body.clientHeight : null;
}

function pageTop(){
	return typeof window.pageYOffset != 'undefined' ? window.pageYOffset : document.documentElement && document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop ? document.body.scrollTop : 0;
}

function pageLeft(){
	return typeof window.pageXOffset != 'undefined' ? window.pageXOffset : document.documentElement && document.documentElement.scrollLeft ? document.documentElement.scrollLeft : document.body.scrollLeft ? document.body.scrollLeft : 0;
}

function showMask(){
	var sWidth,sHeight;
	sWidth = document.body.scrollWidth;
	sWidth = window.screen.availWidth > document.body.scrollWidth ? window.screen.availWidth : document.body.scrollWidth;
	sHeight = window.screen.availHeight > document.body.scrollHeight ? window.screen.availHeight : document.body.scrollHeight;
	var mask = document.createElement("div");
	mask.setAttribute('id','mask');
	mask.style.width = sWidth + "px";
	mask.style.height = sHeight + "px";
	mask.style.zIndex = "5000";
	document.body.appendChild(mask);
}

function hideMask(){
	var mask = document.getElementById("mask");
	if(mask != null){
		if(document.body) document.body.removeChild(mask); 
		else document.documentElement.removeChild(mask); 
	}
}

var dialogs = new Array();

function displayDialog(html){
	var dialog;
	dialog = document.getElementById("dialog");
	if(dialog != null) closeDialog();
	dialog = document.createElement("div");
	dialog.setAttribute('id','dialog');
	dialog.style.zIndex = "6000";
	if(document.all){
		dialog.style.width = "400px";
		dialog.style.height = "235px";
	}
	document.body.appendChild(dialog);
	$$("dialog").setInnerHTML( html + '<iframe src="" frameborder="0" style="position:absolute;visibility:inherit;top:0px;left:0px;width:expression(this.parentNode.offsetWidth);height:expression(this.parentNode.offsetHeight);z-index:-1;filter=\'progid:DXImageTransform.Microsoft.Alpha(style=0,opacity=0)\';"></iframe>');
	var dialog_w = parseInt(dialog.scrollWidth);
	var dialog_h = parseInt(dialog.scrollHeight);
	if(dialog_w < 400){
		dialog.style.width = "400px";
		dialog_w = parseInt(dialog.clientWidth);
	}else{
		dialog.style.width = dialog_w + "px";
	}
	if(dialog_h < 235){
		dialog.style.height = "200px";
		dialog_h = parseInt(dialog.clientHeight);
	}else{
		dialog.style.height = dialog_h + "px";
	}
	var page_w = pageWidth(); 
	var page_h = pageHeight();
	var page_l = pageLeft();
	var page_t = pageTop();

	var dialog_top = page_t + (page_h / 2) - (dialog_h / 2);
	if(dialog_top < page_t) dialog_top = page_t;
	var dialog_left = page_l + (page_w / 2) - (dialog_w / 2);
	if(dialog_left < page_l) dialog_left = page_l + page_w - dialog_w;

	dialog.style.left = dialog_left + "px";
	dialog.style.top =  dialog_top + "px";
	dialog.style.visibility = "visible";

	var dialogx = document.createElement("div");
	dialogx.setAttribute('id','dialogx');
	document.body.appendChild(dialogx);
	dialogx.innerHTML = '<a onclick="closeDialog()" style="cursor:pointer;font-size:14px;font-weight:bold;font-family:Arial;">X</a>';

	dialogx.style.position = "absolute";
	dialogx.style.zIndex = "6500";
	dialogx.style.left = (dialog_left + dialog_w - 10) + "px";
	dialogx.style.top =  (dialog_top + 10) + "px";
}

function openDialog(url, mask){
	if(mask) showMask();
	if(typeof dialogs[url] == 'undefined') Ajax.Request(url,{onLoading:function(){dialogs[url]=this.response; displayDialog('Loading...');}, onComplete:function(){dialogs[url]=this.response; displayDialog(this.response);}});
	else displayDialog(dialogs[url]);
}

function closeDialog(){
	var dialog = document.getElementById("dialog");
	var dialogx = document.getElementById("dialogx");
	var mask = document.getElementById("mask");
	if(document.body){
		document.body.removeChild(dialog); 
		document.body.removeChild(dialogx);
		document.body.removeChild(mask);
	}else{
		document.documentElement.removeChild(dialog); 
		document.documentElement.removeChild(dialogx);
		document.documentElement.removeChild(mask);
	}
	hideMask();
}
function loadJs(url){
	if(arguments.length >= 2 && typeof arguments[1] == 'function') funload = arguments[1];
	if(arguments.length >= 3 && typeof arguments[2] == 'function') funerror = arguments[2];
	var ss=document.getElementsByTagName("script");
	for(i=0;i<ss.length;i++){
		if(ss[i].src && ss[i].src.indexOf(url) != -1){
			if(typeof funload == "function") funload();
			return;
		}
	}
	s=document.createElement("script");
	s.type="text/javascript";
	s.defer = "defer";
	s.src=url;
	document.getElementsByTagName("head")[0].appendChild(s);
	
	s.onload=s.onreadystatechange=function(){
		if(this.readyState && this.readyState=="loading") return;
		if(typeof funload == "function") funload();
	}
	s.onerror=function(){
		this.parentNode.removeChild(this);
		if(typeof funerror == "function") funerror();
	}
}

var in_array = function(arr){
    for(var i=0,k=arr.length;i<k;i++){
        if(this==arr[i].articleno){
            return i;
        }
    }
    return -1;
}
Number.prototype.in_array = in_array;
String.prototype.in_array = in_array;