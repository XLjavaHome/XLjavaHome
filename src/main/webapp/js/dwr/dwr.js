function init() {
    dwr.engine.setActiveReverseAjax(true);
}
function sendMessage() {
    /*JavaChat.addMessage(dwr.util.getValue("text"));*/
    var text = dwr.util.getValue("text");
    ChatService.addMessage(text);
}