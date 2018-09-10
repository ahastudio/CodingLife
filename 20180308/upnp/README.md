# UPnP 테스트

사용 라이브러리: <https://github.com/sidoh/easy_upnp>

절차:

1. SSDP로 IGD(NAT) 발견.
2. WAN IP Connection 서비스로 공개 IP 얻기.
3. WAN IP Connection 서비스로 포트 포워딩.

`AddPortMapping`과 `DeletePortMapping`을 쓸 때 필요한 입력은 다음 문서를 참고:

- http://www.upnp-hacks.org/igd.html
- http://upnp.org/specs/gw/UPnP-gw-WANIPConnection-v1-Service.pdf
