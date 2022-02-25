let token = document.querySelector("meta[name='_csrf']").getAttribute("content");
//let header = document.querySelector("meta[name='_csrf_header']").getAttribute("content");

const findForm=document.querySelector("#findForm")
findForm.addEventListener("submit",findPw);

async function findPw(e){

    e.preventDefault();  //처음부터 이벤트가 발생하는 것을 막아줌( 전송을 막아줌)

    let loginId=document.querySelector("#loginId").value;
    let email=document.querySelector("#email").value;

    if(loginId===null || loginId===""){
        alert("아이디를 입력해 주시기 바랍니다.");
    }

    if(email===null || email===""){
            alert("이메일을 입력해 주시기 바랍니다.");
    }

    let data={
        method:"POST"
        body:JSON.stringify(
            {
                loginId: loginId,
                email: email,
            }
        ),
        headers: {
        'Content-Type': 'application/json',
        'X-CSRF-TOKEN':  token,
        }

    }
    await fetch("http://localhost:8095/mails/find/pw",data)  //해당 url에 대한 추가적인 데이터를 넣어주는거(객체로 받아서 넣어준거)
    .then(
        (response) =>{
            return response.json();
        }
    )
    .then(
        (data)=>{  //이 데이터에는 mailController의 getForgotPassword메소드의 리턴값에 대한 데이터임

        if(!data){   //데이터가 안들어왔을 때
            alert("이메일 발송 실패, 이메일을 확인해 주시기 바랍니다.");
            return;
        }
        else{
          alert("발급된 임시 비밀번호를 입력하신 이메일로 전송하였습니다.");
          window.location.replace("http://localhost:8095/");
        }

        }
    )
    .catch(
        (error)=>{
            console.log(error);
            alert("메일 발송에 실패하였습니다. 이메일 혹은 아이디를 확인하여 주시기 바랍니다.")
        }

    )

}

