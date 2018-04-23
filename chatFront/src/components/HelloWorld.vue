<template>
<div id="app">
  <div>
    <md-field>
      <label>With label</label>
      <md-input v-model="contents" placeholder="A nice placeholder"></md-input>
    </md-field>
  </div>
</div>
</template>

<script>
import {baseurl} from '../constants/url';
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
// import {stomp} from "./stompcommons";

export default{
  name : 'HelloWorld',
  props:['kame'],
  data(){
    return{
      message : [],
      contents : null,
      initial: 'Initial Value',
      type: null,
      withLabel: null,
      inline: null,
      number: null,
      textarea: null,
      autogrow: null,
      disabled: null
    }
  },
  methods:{
    connect:function(){
        this.socket = new SockJS(baseurl+'/chat')
				this.stompClient= Stomp.over(this.socket)
        this.stompClient.connect({},(frame) => {
        this.stompClient.subscribe('/receive/message', function(tick){ 
          console.log(tick)
          
        });
        // this.stompClient.subscribe('/messageBroker/hello', (hello) =>{        
        //   this.message.push(hello.body+"        님이 등장했습니다");
        // });
        // this.stompClient.subscribe('/messageBroker/bye', (hello) => {
        //   this.message.push(hello.body+"        님이 퇴장했다")
        //   this.stompClient.disconnect();
        // })
        // this.sendHello();
      },(error) =>{
        console.log(error);      
        this.connected = false;
			})  
			
    },
    send(){
      this.stompClient.send('/receive/message',JSON.stringify({"name": this.name}),{})
		 
		 },
    sendHello(){
      console.log()
      this.stompClient.send('/receive/hello-client',JSON.stringify(this.name),{})
    },
    disconect(){ 
      if(this.stompClient!=undefined){
          this.stompClient.send('/receive/bye-client',JSON.stringify(this.name),{})
       } 
      console.log("error"+this.stompClient)
    }
  }
}
</script>
<style>
body {
	background-color: #f5f5f5;
}

#main-content {
	max-width: 940px;
	padding: 2em 3em;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
}

.chat-panel .panel-body {
    height: 700px;
    overflow-y: scroll;
}

.chat {
	list-style: none;
	margin: 0px;
	padding: 0px;
}

.chat li {
	margin-bottom: 10px;
	padding-bottom: 5px;
	border-bottom: 1px dotted #999999;
}

.form-group {
	margin-bottom: 0px !important;
}

</style>
