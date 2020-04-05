

var ws = null;
function startServer(){
    var url = 'ws://' + window.location.host + '/message';
    ws = new WebSocket( encodeURI(url) );

    ws.onopen = function(){
        console.log("打开websocket端口")
    };

    ws.onmessage = function(event){
        receiveMessage(event.data);
        console.log("revice message:" + event.data);
    };

    ws.onclose = function(){
        console.log("close connect ..");
    };
}

function sendMessage(obj){
    ws.send(obj);
}