<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://koo-learn.com" prefix="xdf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>${page_title}</title>
</head>
<script type="text/javascript">
var socket;
if (!window.WebSocket) {
	window.WebSocket = window.MozWebSocket;
}
if (window.WebSocket) {
	socket = new WebSocket("ws://localhost:7777/ws");
	socket.onmessage = function(event) {
	var ta = document.getElementById('responseText');
		ta.value = ta.value + '\n' + event.data
	};
	socket.onopen = function(event) {
	var ta = document.getElementById('responseText');
		ta.value = "Web Socket opened!";
	};
	socket.onclose = function(event) {
		var ta = document.getElementById('responseText');
		ta.value = ta.value + "Web Socket closed";
	};
} else {
	alert("Your browser does not support Web Socket.");
}
function send(message) {
	if (!window.WebSocket) { 
		return; 
	}
	if (socket.readyState == WebSocket.OPEN) {
		socket.send(message);
	} else {
		alert("The socket is not open.");
	}
}
</script>
<body>
<form onsubmit="return false;">
<input type="text" name="message" value="Hello, World!"><input
type="button" value="Send Web Socket Data"
onclick="send(this.form.message.value)">
<h3>Output</h3>
<textarea id="responseText" style="width: 500px; height: 300px;"></textarea>
</form>
</body>
</html>
