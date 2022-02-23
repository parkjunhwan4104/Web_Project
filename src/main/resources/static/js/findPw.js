let token = document.querySelector("meta[name='_csrf']").getAttribute("content");
let header = document.querySelector("meta[name='_csrf_header']").getAttribute("content");

const findForm=document.querySelector("#findForm")
findForm.addEventListener("submit",findPw);

async function findPw(){
    let loginId=document.querySelector("#loginId").value;
    let email=document.querySelector("#email").value;

    if(loginId===null || loginId===""){
        alert("아이디를 입력해 주시기 바랍니다.");
    }

    if(email===null || email===""){
            alert("이메일을 입력해 주시기 바랍니다.");
    }

    let data={
        method="POST"
        body:JSON.stringify(
            {
                loginId: loginId,
                email: email,
            }
        ),
        header: {
        'Content-Type': 'application/json'
        'X-CSRF-TOKEN':  token
        }

    }
    await fetch("http://localhost:8095/mails/find/pw",data)
    .then(
        (response) =>{
            return response.json();
        }
    )
    .then(
        (data)=>{

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

