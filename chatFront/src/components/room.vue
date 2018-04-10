<template>
  <div>
      <input v-model="roomname" />방이름
      <button @click="sendroomname">방 만들기</button>
      <div v-for="(res, index) in response">
          <div>{{index+1}}</div>
          <div>{{res}}</div>
      </div>
  </div>
</template>
<script>
import {baseurl} from '../constants/url';
import axios from 'axios'
export default {
  name:"room",
  data(){
      return{
        roomname:null,  
        response : []
      }
  },mounted(){
          axios.get(baseurl+"/").then(res =>{
              console.log("adasd",res)
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
  }
  }
}
</script>
