var stompClient = null;
var wsCreateHandler = null;
var userId = null;
var ipAndPort = null;

function connect() {
	var host = window.location.host; // 带有端口号
	userId =  GetQueryString("userId");
	var socket = new SockJS("http://localhost:9090/websocket-server/websocket");
	//var socket = new SockJS("http://localhost:8071/websocket");
	stompClient = Stomp.over(socket);
	stompClient.connect({}, function (frame) {
		writeToScreen("connected: " + frame);

		// 主题模式: /topic/<topicName>
		// 订阅单发
		stompClient.subscribe("/queue/user" + userId, function (response) {
			writeToScreen(response.body);
		});

		// 订阅群发
		stompClient.subscribe("/topic/chat", function (response) {
			writeToScreen(response.body);
		});

		}, function (error) {
			wsCreateHandler && clearTimeout(wsCreateHandler);
			wsCreateHandler = setTimeout(function () {
				console.log("重连...");
				connect();
				console.log("重连完成");
			}, 1000);
		}
	)
}

function disconnect() {
	if (stompClient != null) {
		stompClient.disconnect();
	}
	writeToScreen("disconnected");
}

function writeToScreen(message) {
	if (DEBUG_FLAG) {
		$("#debuggerInfo").val($("#debuggerInfo").val() + "\n" + message);
	}
}

function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
	var r = window.location.search.substr(1).match(reg);  // 获取url中"?"符后的字符串并正则匹配
	var context = "";
	if (r != null) context = r[2];
	reg = null;
	r = null;
	return context == null || context == "" || context == "undefined" ? "" : context;
}
