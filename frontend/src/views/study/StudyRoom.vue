<template>
<section>
  <div class="container d-flex justify-content-space-between" id="screen">
    <div class="d-flex align-items-stretch justify-content-between col-2">
      <div class="icon">
        <font-awesome-icon type="button" class="fa-2x my-2 ml-2" @click="sidebarArticleOn" icon="edit"/>    <!--수정-->
          <b-sidebar id="sidebar-right" v-model="sidebarArticle" title="스터디 게시판" right shadow>     <!--수정-->
            <div class="px-3 py-2">
              <p class="article">
                <article-modal></article-modal>
                <article-list :studyBoardList="studyBoardList" @getArticleList="getArticleList()"></article-list>
              </p>
            </div>
          </b-sidebar>
        <font-awesome-icon type="button" class="clipboard fa-2x my-2" @click="sidebarHomeworkOn" icon="clipboard"/>    <!--수정-->
        <b-sidebar id="sidebar-right-homework" v-model="sidebarHomework" title="과제 게시판" right shadow>  <!--수정-->
          <div class="px-3 py-2">
            <p class="homework">
              <homework-modal :user="leader"></homework-modal>
              <homework-list :homeworkList="homeworkList" @getHomeworkList="getHomeworkList()"></homework-list>
            </p>
          </div>
        </b-sidebar>
        <font-awesome-icon class="fa-2x my-2" v-b-modal.modal-xl type="button" id="show-btn" @click="calendarShowModal" icon="calendar-week"/> 
          <b-modal ref="calendar-modal" id="modal-xl" size="xl" hide-header hide-footer>
            <calendar :studyLeaderno="leader" :studyno="this.$route.params.study_no" :demoEvents="demoEvents"></calendar>
          </b-modal>
        <font-awesome-icon type="button" class="fa-2x my-2" @click="sidebarDetailOn" icon="info-circle"/>      <!--수정-->
        <b-sidebar id="sidebar-left-study" v-model="sidebarDetail" title="스터디 상세 정보" right shadow>  <!--수정-->
          <div>
            <div id="information" v-for="study in studyInformation" :key="study.id">
              <p class="studyInfo"><font-awesome-icon icon="fa-solid fa-tag" class="fa-1x" /> Name</p>
              <p>{{ study.studyname }}</p>
              <p class="studyInfo"><font-awesome-icon icon="fa-solid fa-user-tag" class="fa-1x" /> Creator</p>
              <p>{{ study.maker }}</p>
              <p class="studyInfo"><font-awesome-icon icon="fa-solid fa-palette" class="fa-1x" /> Category</p>
              <p>{{ study.category }}</p>
              <p class="studyInfo"><font-awesome-icon icon="fa-solid fa-list-ol" class="fa-1x"/> Interests</p>
              <p><badge type="info" class="mx-1" v-for="interest in interestList" :key="interest.id"> {{ interest }}</badge></p>
              <p class="studyInfo"><font-awesome-icon icon="fa-solid fa-newspaper" class="fa-1x"/> Description</p>
              <p>{{ study.description }}</p>
              <update-study-information :studyInformation="study" :interestList="interestList"></update-study-information>
            </div>
          </div>
        </b-sidebar>
        <font-awesome-icon type="button" class="fa-2x my-2" @click="sidebarMemberOn" icon="user-friends"/>      <!--수정-->
        <b-sidebar id="sidebar-left-member" v-model="sidebarMember" title="스터디 회원 목록" right shadow>   <!--수정-->
          <div class="px-5 py-2 mt-3" v-for="members in studyMemberList" :key="members.id">
            <div class="d-flex justify-content-between" v-for="member in members" :key="member.id">
              <div class="d-flex">
                <p v-if="member.image">
                  <img :src="member.image" alt="default-img">
                </p>
                <p v-else>
                  <img src="./../accounts/assets/default.png">
                </p>
                <a id="show-btn" type="button" class="mt-2 ml-3" @click="showModal(member.studyMember)">{{ member.nickname }}</a>    <!--수정-->
              </div>
              <b-button class="mt-1" v-if="userNumber == getLeader() && member.studyMember.userno != getLeader()" style="font-size: 10px; height: 32px; background-color: rgb(235, 124, 124);" @click="userKick(member.studyMember.userno)">강퇴</b-button>
            </div>
          </div>
          <a class="kick" type="button" @click="userKick(userNumber)">회원탈퇴</a>      <!--수정-->
        </b-sidebar>
        <b-modal ref="my-modal" :member="member" hide-header hide-footer>
          <div class="d-block text-center">
            <div v-if="member">
              <user-info-modal :member="member" :studylist="studylist"></user-info-modal>
            </div>
          </div>
        </b-modal>
        <font-awesome-icon type="button" class="fa-2x my-2 ml-2" v-b-toggle.sidebar-right icon="comment" style="display:none" id='cchat'/>
          <b-sidebar id="sidebar-right" title="채팅 게시판" right shadow>
            <div class="px-3 py-2" id="chatting">
            </div>
            <div>
              <textarea id="chatsend" v-model="chatcontent"></textarea>
              <button @click="sendChat" id='chatsendbtn'>Send</button>
            </div>
          </b-sidebar>
        <router-link :to="{ name: 'Main'}">       <!--수정-->
        <h4 class="exit" style="color: white;"><font-awesome-icon icon="fa-solid fa-door-closed" /> 나가기</h4></router-link><!--수정-->
      </div>
      <div id="wrapper">
        <div id="join" class="animate join">
          <form v-on:submit.prevent accept-charset="UTF-8">
            <p class="submit">
            <button @click="register" value="Join!" >화상회의 시작!</button>
            </p>
          </form>
        </div>
        <div id="room" style="display: none;">
        <!--<h2 id="room-header"></h2>
        <video id="videoaa" autoplay></video>  class="d-flex align-items-start" -->
          <div id="participants" class="d-flex align-items-start"></div>  
          <!--video-->
          <div id="videoBig">
            <video id="videoaa"  autoplay controls width="640px" height="480px"></video>
          </div>
          <!--buttons-->
          <div id="buttons" style="display:none">
              <button id="mute" @click="mute">음소거</button>
              <button id="muteoff" @click="muteoff" style="display:none">음성 켜기</button>
              <button id="videooff" @click="videooff" >비디오 중지</button>
              <button id="videoon" @click="videoon" style="display:none">비디오 켜기</button>
              <button id="screenshare" @click="presenter">화면 공유</button>
              <!--<button id="viewer" @click="viewer">화면 공유 받기</button>-->
              <button id="stop" @click="stop" style="display:none">화면 공유 종료</button>
              <button  id="button-leave" @click="leaveRoom"
                value="Leave room">나가기</button>
          </div>
        </div>
      </div>
    </div>

  </div>
</section>
</template>

<script>
import axios from 'axios'
import jwt_decode from 'jwt-decode'
import ArticleModal from '../../components/ArticleModal.vue'
import ArticleList from '../../components/ArticleList.vue'
import HomeworkModal from '@/components/HomeworkModal.vue'
import HomeworkList from '../../components/HomeworkList.vue'
import UpdateStudyInformation from '@/components/UpdateStudyInformation.vue'
import UserInfoModal from '@/components/UserInfoModal.vue'
import Calendar from '@/components/Calendar.vue'
import {Badge} from '../../components'

export default {
  name: 'StudyRoom',
  components: {
    ArticleModal,
    ArticleList,
    HomeworkModal,
    HomeworkList,
    UpdateStudyInformation,
    UserInfoModal,
    Calendar,
    [Badge.name]: Badge
  },
  data: function () {
    return {
      value: '',
      context: null,
      studyBoardList: null,
      homeworkList: null,
      studyMemberList: null,
      studyInformation: null,
      interestList: null,
      studylist: null,
      member: null,
      leader: null,
      demoEvents: [],
      userInfo: {
        userno: null,
        studyno: this.$route.params.study_no
      },
      userNumber: null,
      sidebarHomework: false,   // 수정
      sidebarArticle: false,  // 수정
      sidebarDetail: false, // 수정 
      sidebarMember: false, // 수정
      nickname: null,
      chatcontent:null,
    }
  },
  methods: {
    register,
    leaveRoom,
		mute,
		muteoff,
		videooff,
		videoon,
		//receiveScreen,
		//onNewScreen,
		handleE,
		presenterResponse,
		viewerResponse,
		presenter,
		onOfferPresenter,
		viewer,
		onOfferViewer,
		onIceCandidate,
		stop,
		dispose,
		sendMessage2,
    sendChat,
    makeChat,
    sendMessage3,
    setToken: function () {
      const token = sessionStorage.getItem('jwt')           // 수정
      const config = {
        Authorization: `JWT ${token}`
      }
      return config
    },
    sidebarHomeworkOn() {       // 수정
      this.sidebarArticle=false // 수정
      this.sidebarDetail=false // 수정
      this.sidebarMember=false // 수정
      this.sidebarHomework=!this.sidebarHomework // 수정
    }, // 수정
    sidebarArticleOn() { // 수정
      this.sidebarHomework=false // 수정
      this.sidebarDetail=false // 수정
      this.sidebarMember=false // 수정
      this.sidebarArticle=!this.sidebarArticle // 수정
    }, // 수정
    sidebarDetailOn() { // 수정
      this.sidebarHomework=false // 수정
      this.sidebarArticle=false // 수정
      this.sidebarMember=false // 수정
      this.sidebarDetail=!this.sidebarDetail // 수정
    }, // 수정
    sidebarMemberOn() { // 수정
      this.sidebarHomework=false // 수정
      this.sidebarArticle=false // 수정
      this.sidebarDetail=false // 수정
      this.sidebarMember=!this.sidebarMember // 수정
    },// 수정
    showModal: function (user) {
      this.$refs['my-modal'].show()
      this.getUserInformation(user)
    },
    calendarShowModal: function () {
      this.$refs['calendar-modal'].show()
    },
    getStudyInformation: function () {
      axios({
        method: 'get',
        url: `https://i6a107.p.ssafy.io:8443/api/v1/study/detail/${this.$route.params.study_no}`,
        headers: this.setToken(),
      })
        .then(res => {
          console.log(res.data, 888)
          this.studyInformation = res.data
          const memList= res.data.study.studyMemberList
          var flag = false
          for(var i = 0; i<memList.length;i++){
            if(memList[i].userno==this.userNumber){
              flag = true
              break
            }
          }
          if(!flag){
            this.$router.push({name:'Main'})
          }
          this.interestList = this.studyInformation.study.interests.split("#")
          this.interestList.shift(0)
          console.log(this.interestList)
        })
        .catch(err => {
          console.log(err)
        })
    },
    getArticleList: function () {
      axios({
        method: 'get',
        url: `https://i6a107.p.ssafy.io:8443/api/v1/study_board/boardlist/${this.$route.params.study_no}`,
        headers: this.setToken(),
      })
        .then(res => {
          console.log(res.data.studyBoardList, 456)
          this.studyBoardList = res.data.studyBoardList
        })
        .catch(err => {
          console.log(err)
        })
    },
    getHomeworkList: function () {
      axios({
        method: 'get',
        url: `https://i6a107.p.ssafy.io:8443/api/v1/homework/homeworklist/${this.$route.params.study_no}`,
        headers: this.setToken(),
      })
        .then(res => {
          this.homeworkList = res.data.homeworkList
          console.log(this.homeworkList)
        })
        .catch(err => {
          console.log(err)
        })
    },
    getStudyMemberList: function () {
      axios({
        method: 'get',
        url: `https://i6a107.p.ssafy.io:8443/api/v1/study/memberList/${this.$route.params.study_no}`,
        headers: this.setToken(),
      })
        .then(res => {
          this.studyMemberList = res.data
          console.log(this.studyMemberList, 111)
        })
        .catch(err => {
          console.log(err)
        })
    },
    getUserInformation: function (user) {
      axios({
        method: 'get',
        url: `https://i6a107.p.ssafy.io:8443/api/v1/admin/${user.userno}/`,
        headers: this.setToken(),
      })
        .then(res => {
          console.log(res)
          this.member = res.data.user
          this.studylist = res.data.studyList
        })
        .catch(err => {
          console.log(err)
        })
    },
     getCalendar: function () {
      axios({
        method: 'get',
        url: `https://i6a107.p.ssafy.io:8443/api/v1/study/calendar/${this.$route.params.study_no}`,
      })
      .then(res => {
        const homeworkList = res.data.homeworkList
        for(var i = 0; i < homeworkList.length;i++){
          this.demoEvents.push ({
            title: homeworkList[i].title,
            start: homeworkList[i].endDate,
            end: homeworkList[i].endDate,
            isHomework:true,
            eventno:homeworkList[i].homeworkno,
          })
        }
        const scheduleList = res.data.scheduleList
        for(i = 0; i<scheduleList.length;i++){
          this.demoEvents.push({
            title: "스터디 모임",
            start: scheduleList[i].nextDate,
            end: scheduleList[i].nextDate,
            isHomework:false,
            eventno:scheduleList[i].scheduleno,
          })
        }
        
        })
        .catch(err => {
          console.log(err)
        })
    },
    userKick: function (userno) {
      this.userInfo.userno = userno
      axios({
        method: 'post',
        url: `https://i6a107.p.ssafy.io:8443/api/v1/admin/kick`,
        data: this.userInfo
      })
        .then(res => {
          if (this.userNumber == this.userInfo.userno) {
            console.log(res, this.userInfo)
            alert("스터디에서 탈퇴하였습니다.")
            this.$router.push({name: 'Main'})
          } else {
            console.log(res, this.userInfo)
            alert("스터디에서 해당 회원을 강퇴시켰습니다.")
            window.location.reload()
          }
        })
        .catch(err => {
          console.log(err, this.userInfo)
        })
    },
    getLeader: function () {
      for (let index = 0; index < this.studyMemberList.studyMemberList.length; index++) {
        if (this.studyMemberList.studyMemberList[index].studyMember.position == 0) {
          this.leader = this.studyMemberList.studyMemberList[index].studyMember.userno
          return this.leader
        }
      }
    }
  },
  created: function () {
    if (sessionStorage.getItem('jwt')) {                  // 수정
      const token = sessionStorage.getItem('jwt')                   // 수정
      const decoded = jwt_decode(token)
      this.userNumber = decoded.userno
    } else {
      this.$router.push({name: 'Login'})
    }
    this.getStudyMemberList()
    this.getArticleList()
    this.getHomeworkList()
    this.getStudyInformation()
    this.getCalendar()
    
  }
}
//variables
var ws = new WebSocket('wss://'+location.host+'/groupcall');
var ws2 = new WebSocket('wss://' + location.host + '/call');
var ws3 = new WebSocket('wss://' + location.host + '/chat');
var participants = {};
var name;
var videoj;
var webRtcPeer;
const PARTICIPANT_MAIN_CLASS = 'participant';
const PARTICIPANT_CLASS = 'participant';

window.onbeforeunload = function() {
	ws.close();
  ws2.close();
  ws3.close();
};

window.onload = function() {
	// console = new Console();
	videoj = document.getElementById('videoaa');
	// disableStopButton();
}

ws.onclose = function() {
    setTimeout(socketInit, 300); // 웹소켓을 재연결하는 코드 삽입
};

ws2.onclose = function() {
    setTimeout(socketInit, 300); // 웹소켓을 재연결하는 코드 삽입
};

ws3.onclose = function() {
    setTimeout(socketInit, 300); // 웹소켓을 재연결하는 코드 삽입
};

function Participant(name) {
	this.name = name;
	var container = document.createElement('div');
	container.className = PARTICIPANT_CLASS;
	container.id = name;
	var span = document.createElement('span');
	var video = document.createElement('video');
	var rtcPeer;
  // 여기에다가 css setAttribute로 넣어야함(그냥 style에선 안먹힘)
  var createMainDivStyle = "border-radius: 4px; margin-left: 5px; margin-right: 5px;	width: 150px;	text-align: center;overflow: hide;	float: left;padding: 5px;border-radius: 10px;";
  container.setAttribute("style", createMainDivStyle);
	
  var videostyle = "width:100%;";
  video.setAttribute("style", videostyle);
  var spanStyle = "color : white;";
  span.setAttribute("style", spanStyle);
  container.appendChild(video);
	container.appendChild(span);
	container.onclick = switchContainerClass;
	document.getElementById('participants').appendChild(container);
	span.appendChild(document.createTextNode(name));

	video.id = 'video-' + name;
	video.autoplay = true;
	video.controls = true;


	// video.muted = true;

	this.getElement = function() {
		return container;
	}

	this.getVideoElement = function() {
		return video;
	}

	function switchContainerClass() {
		if (container.className === PARTICIPANT_CLASS) {
			var elements = Array.prototype.slice.call(document.getElementsByClassName(PARTICIPANT_MAIN_CLASS));
			elements.forEach(function(item) {
					item.className = PARTICIPANT_CLASS;
				});

				container.className = PARTICIPANT_MAIN_CLASS;
			} else {
			container.className = PARTICIPANT_CLASS;
		}
	}

	function isPresentMainParticipant() {
		return ((document.getElementsByClassName(PARTICIPANT_MAIN_CLASS)).length != 0);
	}

	this.offerToReceiveVideo = function(error, offerSdp, wp){
		if (error) return console.error ("sdp offer error")
		console.log('Invoking SDP offer callback function');
		var msg =  { id : "receiveVideoFrom",
				sender : name,
				sdpOffer : offerSdp
			};
		sendMessage(msg);
	}


	this.onIceCandidate = function (candidate, wp) {
		console.log("Local candidate" + JSON.stringify(candidate));

		var message = {
			id: 'onIceCandidate',
			candidate: candidate,
			name: name
		};
		sendMessage(message);
	}

	Object.defineProperty(this, 'rtcPeer', { writable: true});

	this.dispose = function() {
		console.log('Disposing participant ' + this.name);
		this.rtcPeer.dispose();
		container.parentNode.removeChild(container);
	};
}

//web socket message condition
ws.onmessage = function(message) {
	var parsedMessage = JSON.parse(message.data);
	console.info('Received message: ' + message.data);

	switch (parsedMessage.id) {
	case 'existingParticipants':
		onExistingParticipants(parsedMessage);
		break;
	case 'newParticipantArrived':
		onNewParticipant(parsedMessage);
		break;
	case 'participantLeft':
		onParticipantLeft(parsedMessage);
		break;
	case 'receiveVideoAnswer':
		receiveVideoResponse(parsedMessage);
		break;
	case 'iceCandidate':
	console.log("iceCandidate receive check");
		participants[parsedMessage.name].rtcPeer.addIceCandidate(parsedMessage.candidate, function (error) {
			if (error) {
				console.error("Error adding candidate: " + error);
				return;
			}
		});
		break;	
	default:
		console.error('Unrecognized message', parsedMessage);
	}
}

ws2.onmessage = function(message) {
	var parsedMessage = JSON.parse(message.data);
	console.info('Received message: ' + message.data);

	switch (parsedMessage.id) {
	case 'iceCandidate':
	  console.log("iceCandidate receive check");
		webRtcPeer.addIceCandidate(parsedMessage.candidate, function(error) {
			if (error)
				return console.error('Error adding candidate: ' + error);
			});
		break;
	case 'presenterResponse':
		presenterResponse(parsedMessage);
		break;
	case 'viewerResponse':
		viewerResponse(parsedMessage);
		break;
	case 'stopCommunication':
		dispose();
		break;
  case 'newPresenter' :
    viewer();
	default:
		console.error('Unrecognized message', parsedMessage);
	}
}

ws3.onmessage = function(message) {
	var parsedMessage = JSON.parse(message.data);
	console.info('Received message: ' + message.data);

	switch (parsedMessage.id) {
	case 'MessageEvent':
	  makeChat(parsedMessage);
		break;
	default:
		console.error('Unrecognized message', parsedMessage);
	}
}

function sendChat(){
  var content = this.chatcontent;
  var message = {
    id : 'newMessage',
    sender : name,
    content : content
  }
  sendMessage3(message);
  this.chatcontent = null;
}

function makeChat(message){
  var diva = document.createElement('div');
  var sender = document.createElement('p');
  var content = document.createElement('p');
  sender.appendChild(document.createTextNode(message.sender));
  content.appendChild(document.createTextNode(message.content));
  var senderStyle = "text-align: left;color: cadetblue;margin: 0;";
  sender.setAttribute("style", senderStyle);
  var contentStyle = "text-align: left;";
  content.setAttribute("style", contentStyle);
  diva.appendChild(sender);
  diva.appendChild(content);
  document.getElementById('chatting').append(diva);
}

//register
function register() {
	name = this.nickname
	var room = this.studyInformation.study.studyname;
	document.getElementById('join').style.display = 'none';
	document.getElementById('room').style.display = 'block';
  document.getElementById('buttons').style.display = 'block';
  document.getElementById('cchat').style.display = 'block';

  //시작시간 저장
  // DATE 객체
  var NOW_DATE = new Date(); 
  // UTC 시간 계산
  const UTC = NOW_DATE.getTime() + (NOW_DATE.getTimezoneOffset() * 60 * 1000); 
  //포맷 그대로 넣기 위해서 JSON.stringify사용
  localStorage.setItem("pastTime", JSON.stringify(UTC));

	var message = {
		id : 'joinRoom',
		name : name,
		room : room,
	}
	sendMessage(message);
  sendMessage2(message);
  sendMessage3(message);
}

//screen share start
function presenterResponse(message) {
	console.log("presenterResponse arrived");
	if (message.response != 'accepted') {
		var errorMsg = message.message ? message.message : 'Unknow error';
		console.info('Call not accepted for the following reason: ' + errorMsg);
		dispose();
	} else {
		console.log('Call');
		webRtcPeer.processAnswer(message.sdpAnswer, function(error) {
			if (error)
				return console.error(error);
		});
    document.getElementById('stop').style.display = 'inline';
    document.getElementById('screenshare').style.display = 'none';

	}
}

function viewerResponse(message) {
	console.log("viewerResponse arrived");
	if (message.response != 'accepted') {
		var errorMsg = message.message ? message.message : 'Unknow error';
		console.log('Call not accepted for the following reason: ' + errorMsg);
		dispose();
	} else {
		console.log("viewerResponse accepted but not working yet");
		webRtcPeer.processAnswer(message.sdpAnswer, function(error) {
			if (error)
				return console.error(error);
		});
		console.log("accepted");
	}
}

function presenter() {
	console.log('pre. start');
	if (!webRtcPeer) {
		//showSpinner(videoj);
		var constraints = {
		audio : true,
		video : {
			mandatory : {
				maxWidth : 800,
				maxFrameRate : 15,
				minFrameRate : 15
			}
		}
	};
		navigator.mediaDevices.getDisplayMedia(displayMediaOptions).then(function handle(stream){
		var options = {
			localVideo: videoj,
			videoStream : stream,
			mediaConstraints: constraints,
			onicecandidate: onIceCandidate,
			sendSource : 'screen'
		}
		webRtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerSendonly(options,
				function(error) {
					if (error) {
						return console.error(error);
					}
					webRtcPeer.generateOffer(onOfferPresenter);
				});




		}, handleE);
		
		// enableStopButton();
	}
}

function onOfferPresenter(error, offerSdp) {
	if (error)
		return console.error('Error generating the offer');
	console.info('Invoking SDP offer callback function ' + location.host);
	var message = {
		id : 'presenter',
		sdpOffer : offerSdp
	}
	sendMessage2(message);
}

function viewer() {
	if (!webRtcPeer) {
		//showSpinner(videoj);

		var options = {
			remoteVideo : videoj,
			onicecandidate : onIceCandidate,
			sendSource : 'screen'
		}
		webRtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerRecvonly(options,
				function(error) {
					if (error) {
						return console.error(error);
					}
					this.generateOffer(onOfferViewer);
					console.log("viewer peer connect success");
				});

		// enableStopButton();
	}
}

function onOfferViewer(error, offerSdp) {
	if (error) return console.error('Error generating the offer');
	console.info('Invoking SDP offer callback function ' + location.host);
	var message = {
		id : 'viewer',
		sdpOffer : offerSdp
	}
	sendMessage2(message);
}

function onIceCandidate(candidate) {
	console.log("One2Many candidate" + JSON.stringify(candidate));

	var message = {
		id : 'onIceCandidate',
		candidate : candidate
	};
	sendMessage2(message);
}

function stop() {
	var message = {
		id : 'stop'
	}
	sendMessage2(message);
  document.getElementById('stop').style.display = 'none';
  document.getElementById('screenshare').style.display = 'inline';
	dispose();
}

function dispose() {
	if (webRtcPeer) {
		webRtcPeer.dispose();
		webRtcPeer = null;
	}
	// disableStopButton();
}

function sendMessage2(message) {
	var jsonMessage = JSON.stringify(message);
	console.log('Sending message: ' + jsonMessage);
	ws2.send(jsonMessage);
}

function handleE(){
	console.log("handelE");
}
//screen share end

//conference start
function onNewParticipant(request) {
	receiveVideo(request.name);
}



function receiveVideoResponse(result) {
	participants[result.name].rtcPeer.processAnswer (result.sdpAnswer, function (error) {
		if (error) return console.error (error);
	});
}

function callResponse(message) {
	if (message.response != 'accepted') {
		console.info('Call not accepted by peer. Closing call');
		stop();
	} else {
		webRtcPeer.processAnswer(message.sdpAnswer, function (error) {
			if (error) return console.error (error);
		});
	}
}


function onExistingParticipants(msg) {
	var constraints = {
		audio : true,
		video : {
			mandatory : {
				maxWidth : 320,
				maxFrameRate : 15,
				minFrameRate : 15
			}
		}
	};
	console.log(name + " registered in room " + room);
	var participant = new Participant(name);
	participants[name] = participant;
	var video = participant.getVideoElement();
  console.log("participant 생성");
	var options = {
			localVideo: video,
			mediaConstraints: constraints,
			onicecandidate: participant.onIceCandidate.bind(participant)
		}
	participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerSendonly(options,
		function (error) {
			if(error) {
				return console.error(error);
			}
			this.generateOffer (participant.offerToReceiveVideo.bind(participant));
	});

	msg.data.forEach(receiveVideo);
}



function leaveRoom() {
  //시간계산 시작
  var NOW_DATE = new Date();
  // UTC 시간 계산
  const UTC = NOW_DATE.getTime() + (NOW_DATE.getTimezoneOffset() * 60 * 1000); 

  //과거 시간 가져오기
  const PAST_DATE = JSON.parse(localStorage.getItem('pastTime'));
  console.log(PAST_DATE);

  // 현재 시간 - 과거 현재 = 회의 시간
  var millisec = UTC - PAST_DATE;
  console.log(millisec);
  // 리턴값 데이터 포맷 실시
  var hour = Math.floor(((millisec / (1000 * 60 *60 )) % 24 ));
  var min = Math.floor(((millisec / (1000 * 60 )) % 60 ));
  var sec = Math.floor((millisec / 1000 ) % 60) ;
  // : 로 파싱
  var time = hour+":"+min+":"+sec;
  console.log(time);
  var temp1 = this.userNumber;
  var temp2 = this.studyInformation.study.studyno;

  console.log(temp1)
  console.log(temp2)
  var data={
    time : time,
    userno : temp1,
    studyno : temp2
  }
  axios({
        method: 'post',
        url: 'https://i6a107.p.ssafy.io:8443/api/v1/study/time/',
        data: data
      })
        .then(res => {
          console.log(res)
          console.log("success")
        })
        .catch(err => {
          console.log(err)
        })
	console.log("나가기 누름");
	sendMessage({
		id : 'leaveRoom'
	});

	for ( var key in participants) {
		participants[key].dispose();
	}

	document.getElementById('join').style.display = 'block';
	document.getElementById('room').style.display = 'none';
  document.getElementById('buttons').style.display = 'none';
  document.getElementById('cchat').style.display = 'none';

	ws.close();
	location.reload();
}

function receiveVideo(sender) {
	var participant = new Participant(sender);
	participants[sender] = participant;
	var video = participant.getVideoElement();

	var options = {
		remoteVideo: video,
		onicecandidate: participant.onIceCandidate.bind(participant)
    }

	participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerRecvonly(options,
			function (error) {
				if(error) {
					return console.error(error);
				}
				this.generateOffer (participant.offerToReceiveVideo.bind(participant));
	});;
}

function onParticipantLeft(request) {
	console.log('Participant ' + request.name + ' left');
	var participant = participants[request.name];
	participant.dispose();
	delete participants[request.name];
}

function sendMessage(message) {
	var jsonMessage = JSON.stringify(message);
	console.log('Sending message: ' + jsonMessage);
	ws.send(jsonMessage);
}

function sendMessage3(message) {
	var jsonMessage = JSON.stringify(message);
	console.log('Sending message: ' + jsonMessage);
	ws3.send(jsonMessage);
}

function mute(){
	console.log(name + "muted");
	participants[name].rtcPeer.audioEnabled = false;
	document.getElementById('mute').style.display = 'none';
	document.getElementById('muteoff').style.display = 'inline';
	
}

function muteoff(){
	participants[name].rtcPeer.audioEnabled = true;
	document.getElementById('muteoff').style.display = 'none';
	document.getElementById('mute').style.display = 'inline';
}

function videooff(){
	participants[name].rtcPeer.videoEnabled = false;
	document.getElementById('videooff').style.display = 'none';
	document.getElementById('videoon').style.display = 'inline';
}

function videoon(){
	participants[name].rtcPeer.videoEnabled = true;
	document.getElementById('videooff').style.display = 'inline';
	document.getElementById('videoon').style.display = 'none';
}

var displayMediaOptions = {
  video: {
    cursor: "always"
  },
  audio: false
};

</script>

<style scoped>
  section {
    background-image: url("./assets/board.jpg");
    background-size: contain;
    height: 100vh;
    background-repeat: repeat-x;
    margin-top: 4rem;                 /* 수정 */
  }
  .icon {
    display: flex;
    flex-direction: column;
    margin-top: 5rem;
    color: white;
  }
  .icon2 {
    align-items:flex-end
  }
  .kick {
    position: fixed;
    bottom: 8px;
    right: 16px;
  }
  .clipboard {
    margin-left: 1px;
  }
  button { 
    font-size: 11px; 
    height: 38px; 
    background-color: white; 
  } 
  button:hover { 
    background-color: rgb(79, 138, 216); 
  }
  .article {
    display: flex;
    flex-direction: column;
  }
  .homework {
    display: flex;
    flex-direction: column;
  }
  img {
    width: 40px;
    height: 40px;
    border-radius: 70%;
  }
  a {
    color: black;
  }
  #information {
    margin-top: 1rem;
    display: flex;
    flex-direction: column;
  }
  .studyInfo {
    margin: 0;
    font-size: 25px;
  }
  .exit {
    margin-top: 15rem;
  }
  #chatting{
    height: 85%;
    background: white;
    width: 90%;
    align-content: center;
    margin: 19px;
    overflow: auto;
  }

  #chatsendbtn{
    width: 50px;
    margin-left: 10px;
    height: 31px;
  }

  #wrapper{
    height : 50%;
  }

  #join{
    margin-top : 50%;
  }
</style>
