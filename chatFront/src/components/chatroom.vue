<template>
  <div>
      <textarea cols="100" rows="40" class="texta" v-model="message"></textarea>
      <br />
      <div class="ipt">
      <input  class="opt" type="text" v-model="content" />
      <button @click="send">보내기</button>
      </div>
  </div>
</template>
<script>
import {baseurl} from '../constants/url';
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";

export default {
  name:"chatroom",
  props:['name','roomname','roomId'],
  data(){
      return{
        message:[],
        content:null
      }
  },
  mounted(){
      this.socket = new SockJS(baseurl+'/chat')
	  this.stompClient= Stomp.over(this.socket)
      this.stompClient.connect({},(frame)=>{
          this.stompClient.subscribe('/messageBroker/room/'+this.roomId,(tick)=>{
              let m = JSON.parse(tick.body);
              if(m.username!=this.name){
                  if(m.contents==null){
                      this.message.push(m.username+"님이 등장하셨습니다.")
                  }else{
                      this.message.push(m.username+"  :  "+m.contents)
                  }
                  
              }
            
          });
          this.stompClient.subscribe('/messageBroker/room/alert/'+this.roomId,(tick)=>{
              let m = JSON.parse(tick.body);
            alert(m);
          });
          this.stompClient.send('/receive/'+this.roomId,JSON.stringify({'username':this.name,'contents':this.content}),{})
      })
    
},
methods:{
    send:function(){
         this.stompClient.send('/receive/'+this.roomId+'/message',JSON.stringify({'username':this.name,'contents':this.content}),{})
    }
}
}
</script>
<style>
.texta{
    overflow:scroll;
}
.ipt{
    width: 54%;
}
.ipt .opt{
    width: 91%;
}
</style>
