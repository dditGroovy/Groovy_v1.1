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
        log.info("msg: {}", msg);
        if (msg!=null) {
            String[] msgs = msg.split(",");

            if (msgs!=null && msgs.length == 2) {
                String category = msgs[0];
                String url = msgs[1];
                if (category.equals("noti")) {
                    for (WebSocketSession webSocketSession : sessions) {
                        if (webSocketSession.isOpen()) {
                            webSocketSession.sendMessage(new TextMessage
                                                        ("<a href=\" "+ url +"\">"+
                                                        "<h1>[전체공지]</h1>" +
                                                        "<p>관리자로부터 전체 공지사항이 등록되었습니다.</p>" +
                                                        "</a>"));
                        }
                    }

                }
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
