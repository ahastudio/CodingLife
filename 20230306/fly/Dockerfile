FROM pierrezemb/gostatic

COPY ./public/* /srv/http/

ENTRYPOINT ["/goStatic", "-port", "8080", "-fallback", "/index.html"]
