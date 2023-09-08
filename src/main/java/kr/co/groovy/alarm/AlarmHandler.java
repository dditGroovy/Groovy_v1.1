package kr.co.groovy.alarm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class AlarmHandler extends TextWebSocketHandler {
    //로그인 한 전체
    List<WebSocketSession> sessions = new ArrayList<>();

    //1:1
    Map<String, WebSocketSession> userSessionMap = new HashMap<>();

    //서버 접속성공
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("Socket 연결");
        sessions.add(session);
        log.info("현재 접속한 사원 id: {}", currentUserName(session));
        String senderId = currentUserName(session);
        userSessionMap.put(senderId, session);
    }

    private String currentUserName(WebSocketSession session) {
        String loginUserId;

        if (session.getPrincipal() == null) {
            loginUserId = null;
        } else {
            loginUserId = session.getPrincipal().getName();
        }
        return loginUserId;
    }

    //소켓에 메시지 보냈을 때
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload(); //javascript 넘어온 message

        if(msg != null) {
            String[] msgs = msg.split(",");
            if(msgs != null && msgs.length ==3) { //메시지가 왔을 때 조건 설정 부분

//                WebSocketSession receiversession = userSessionMap.get() //접속 중일 때 알람 보낸다..!

                  //보내는 조건 설정
//                if(receiversession !=null) {
//                    TextMessage txtmsg = new TextMessage(mid+"님이" + receiver + "님의" + btitle + comment);
//                    receiversession.sendMessage(txtmsg);//작성자에게 알려줍니다
//                }else {
//                    TextMessage txtmsg = new TextMessage(mid+"님이" + receiver + "님의" + btitle + comment);
//                    session.sendMessage(txtmsg);//보내지는지 체크하기
//                }

            }

        }
    }

    //연결 해제될 때
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("socket END");
        sessions.remove(session);
        userSessionMap.remove(currentUserName(session), session);
    }
}
