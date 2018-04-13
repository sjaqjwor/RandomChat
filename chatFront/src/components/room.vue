<template>
  <div >
      <div v-if="check==1">
      <input v-model="roomname" />방이름
      <button @click="sendroomname">방 만들기</button>
      <div v-for="(res, index) in response">
          <div @click="enterroom(index+1)"> {{res}}</div>
      </div>
      </div>
      <chatroom v-else-if="check==2" :name='name' :roomname='roomname' :roomId='roomId'></chatroom>
  </div>

</template>
<script>
import {baseurl} from '../constants/url';
import chatroom from './chatroom'
import axios from 'axios'

export default {
  name:"room",
  components:{
      chatroom
  },
  props:['name'],
  data(){
      return{
        username:null,
        roomId: 0,
        check:1,
        roomname:null,  
        response : []
      }
  },mounted(){
          axios.get(baseurl+"/").then(res =>{
          this.response=res.data.rooms.map(s=>{
              console.log(s);
              return s.roomname;
          })
        })
        },
  methods:{
      sendroomname:function(params) {
           axios.post(baseurl+"/made",{roomname:this.roomname}).then(response =>{
          this.response=response.data.rooms.map(s=>{
              console.log(s);
              return s.roomname;
          }
          )
      }).catch(e=>{
          console.log(e)
      })
      this.enterroom(1);
  },
  enterroom:function(params){
      this.check=2
      this.roomId=params
      this.username=this.name
  }
}
}
</script>
