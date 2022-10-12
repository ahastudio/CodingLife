FROM pierrezemb/gostatic

COPY ./build/ /srv/http/

ENTRYPOINT ["/goStatic", "-port", "8080", "-fallback", "/index.html"]
