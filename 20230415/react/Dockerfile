FROM pierrezemb/gostatic

COPY ./dist/* /srv/http/

ENTRYPOINT ["/goStatic", "-port", "8080", "-fallback", "/index.html"]
