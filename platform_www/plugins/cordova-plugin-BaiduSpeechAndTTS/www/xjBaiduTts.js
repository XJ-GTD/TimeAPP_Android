cordova.define("cordova-plugin-BaiduSpeechAndTTS.XjBaiduTts", function(require, exports, module) {
var exec = require('cordova/exec');


var xjBaiduTtsApi = {
    startSpeak:function(success,error,speakMessage){
        exec(success,error,"XjBaiduTts","speak",[speakMessage]);
    },
    speakStop:function(){
        exec(null,null,"XjBaiduTts","stop",[]);
    }
};

module.exports = xjBaiduTtsApi;

});
