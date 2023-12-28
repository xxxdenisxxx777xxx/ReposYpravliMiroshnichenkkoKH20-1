const http = require('http');

function parseHeaders(req) {
  console.log(req.method + ' ' + req.url + ' HTTP/' + req.httpVersion);

  for (let [key, value] of Object.entries(req.headers)) {
    key = key.replace(/^\w|-\w/g, s => s.toUpperCase());
    console.log(`${key}: ${value}`);
  }

  console.log('');
}

function startServer(port, host) {
  http.createServer((req, resp) => {
    req.setEncoding('utf-8');

    parseHeaders(req);

    req.on('data', console.log);

    resp.writeHead(200);
    resp.end();
  }).listen(port, host);

  console.error(`Listen server on ${host}:${port}`);
}

const host = process.argv[3] || '127.0.0.1';
const port = parseInt(process.argv[2], 10) || 8080;

startServer(port, host);
