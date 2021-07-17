# Proxy storage

A small server which tries to give you back a valid working proxy address for HTTP/HTTPS use.

### Running

Making use of Docker Compose is going to be the easiest option:

```yaml
version: "3.7"
services:
  proxy-storage:
    image: peavers:proxy-storage:latest
    container_name: proxy-storage
    restart: unless-stopped
    depends_on:
      - proxy-mongo
    environment:
      - SPRING_DATA_MONGODB_HOST=proxy-mongo
    ports:
      - 8080:8080

  proxy-mongo:
    image: mongo:latest
    container_name: proxy-mongo
    restart: unless-stopped
    ports:
      - 27017:27017
    volumes:
      - proxy-mongo-data:/data/db

volumes:
  proxy-mongo-data:

```

### Loading proxies
A list of proxies can be loaded into the service via:

```shell
curl --location --request POST 'http://localhost:8080/api/proxy' --form 'file=@"~/proxies.txt"'
```

The expected format is a pain text file as such
```yaml
192.168.0.1:8080
192.168.0.2:8080
192.168.0.3:8080
192.168.0.4:8080
```

### Retrieving a proxy

Fetching a validated proxy from the database is as simple as

```shell
curl --location --request GET 'http://localhost:8080/api/proxy'```
```

This will return a `Proxy` object ready to be consumed by your downstream application

```json
{
  "id": "60f2ab60ad1d64276a455ff3",
  "value": "192.168.0.3:8080",
  "validated": true
}
```

### Validation

Once every 6 hours (configurable) the service will attempt to call each proxy stored and check for a 200 response. If a
200 is returned the proxy is flagged as validated. If anything else happens during the connection the proxy is assumed
dead and removed from the database.
