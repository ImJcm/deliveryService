const host = 'http://' + window.location.host;


function logout() {
    // 토큰 삭제 - 토큰 만료일을 과거로 설정하여 토근 제거
    name = "Authorization"
    document.cookie = name + '=; expires=Thu, 01 Jan 1999 00:00:10 GMT;';
    window.location.href = host + "/api/user/login-page";
}

function goHome() {
    window.location.href = host;
}

function goLogin() {
    window.location.href = host + '/api/member/login-page';
}

function goMypage() {
    window.location.href = host + '/api/member/profile';
}

function goSignup() {
    window.location.href = host + '/api/member/signup';
}

function showButtons() {
    console.log("showButtons() 실행")
    const loginButton = document.getElementById('loginButton'); // 로그인 버튼 요소를 가져옴
    const logoutButton = document.getElementById('logoutButton'); // 로그아웃 버튼 요소를 가져옴
    const signupButton = document.getElementById('signupButton'); // 로그아웃 버튼 요소를 가져옴
    const mypageButton = document.getElementById('mypageButton'); // 로그아웃 버튼 요소를 가져옴
    const jwtToken = getJwtFromCookie(); // Request 헤더에서 JWT 토큰을 가져옴
    //const jwtToken=getToken();

    if (jwtToken) {
        loginButton.style.display = 'none'; // JWT 토큰이 존재하면 로그인 버튼을 감춤
        signupButton.style.display = 'none';
        logoutButton.style.display = 'inline'; // JWT 토큰이 존재하면 로그아웃 버튼을 표시
        mypageButton.style.display = 'inline';
    } else {
        loginButton.style.display = 'inline'; // JWT 토큰이 존재하지 않으면 로그인 버튼을 표시
        signupButton.style.display = 'inline';
        logoutButton.style.display = 'none'; // JWT 토큰이 존재하지 않으면 로그아웃 버튼을 감춤
        mypageButton.style.display = 'none';
    }
}

function getJwtFromCookie() {
    const cookieName = 'Authorization'; // JWT가 저장된 쿠키의 이름
    const cookies = document.cookie.split(';');

    for (let i = 0; i < cookies.length; i++) {
        const cookie = cookies[i].trim();

        if (cookie.startsWith(`${cookieName}=`)) {
            const jwtCookie = cookie.substring(cookieName.length + 1);
            return jwtCookie;
        }
    }

    return null; // JWT가 존재하지 않는 경우 null 반환
}


function getToken() {

    let auth = Cookies.get('Authorization');

    if (auth === undefined) {
        return '';
    }

    // kakao 로그인 사용한 경우 Bearer 추가
    if (auth.indexOf('Bearer') === -1 && auth !== '') {
        auth = 'Bearer ' + auth;
    }

    return auth;
}

function isValidContents(contents) {
    if (contents == '') {
        alert('내용을 입력해주세요');
        return false;
    }
    if (contents.trim().length <= 10) {
        alert('10자 이상 입력해주세요.');
        return false;
    }
    return true;
}