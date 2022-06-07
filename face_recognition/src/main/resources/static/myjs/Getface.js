$(function(){
	let mediaStreamTrack=null;
	openMedia();
	setTimeout("tishi()","1000");
	setTimeout("tishi2()","3000");
	setTimeout("takePhoto()","5000");
	
})
var number=0;
function tishi(){
	$("#flag").html("正在打开摄像头")
}
function tishi2(){
	$("#flag").html("请正视摄像头")
}
function tishi3(){
    window.location.href="/";
}
    function openMedia() {
        let constraints = {
            video: { width: 500, height: 500 },
            audio: false
        };
        //获得video摄像头
        let video = document.getElementById('video');     
        let promise = navigator.mediaDevices.getUserMedia(constraints);
        promise.then((mediaStream) => {
            mediaStreamTrack = typeof mediaStream.stop === 'function' ? mediaStream : mediaStream.getTracks()[1];
            video.srcObject = mediaStream;
            video.play();
        });
    }

    // 拍照
    function takePhoto() {
        //获得Canvas对象
        number++;
        let video = document.getElementById('video');
        let canvas = document.getElementById('canvas');
        let ctx = canvas.getContext('2d');
        ctx.drawImage(video, 0, 0, 500, 500);
		// toDataURL  ---  可传入'image/png'---默认, 'image/jpeg'
        let img = document.getElementById('canvas').toDataURL();
        // 这里的img就是得到的图片
        console.log('img-----', img);
        document.getElementById('imgTag').src=img;
		$("#flag").html("正在识别");
        $.ajax({
            url:"/face/face-login",    //请求的url地址
            dataType:"json",   //返回格式为json
            async:true,//请求是否异步，默认为异步，这也是ajax重要特性
            // contentType:"application/json",
            data: {"imagebast64": img} , //参数值
            type: "POST", //请求方式
            success: function () {
                    window.location.href="/face/success"
            }
            })

    }

    // 关闭摄像头
    function closeMedia() {
        mediaStreamTrack.stop();
    }