<template>
  <div>
      <input v-if="page==0" v-model="name">
      <button v-if="page==0" @click="send">send</button>
      <room v-if="page==1" ></room>
      <HelloWorld v-if="page==2" :name="name" ></HelloWorld>
  </div>
</template>

<<script>
import {baseurl} from './constants/url';
import SockJS from "sockjs-client";
import Stomp from "webstomp-client";
import axios from 'axios'
import HelloWorld from './components/HelloWorld'
import room from './components/room'

export default{
        name : 'App',
        components:{
          HelloWorld,room
        },
        data(){
            return{
              name :null,
              page : 0
            }
        },
        methods:{
            send : function(params) {
              this.page = 1
            }

        }
    }
</script>
