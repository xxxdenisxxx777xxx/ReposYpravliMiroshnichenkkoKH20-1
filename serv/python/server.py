import socket
import sys

host = sys.argv[2] if len(sys.argv) >= 3 else '127.0.0.1'
port = int(sys.argv[1]) if len(sys.argv) >= 2 else 8080

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

try:
    s.bind((host, port))
    s.listen(1)
    sys.stderr.write(f'Listen server on {host}:{port}\n\n')

    while 1:
        conn, addr = s.accept()
        data = ''
        while 1:
            chunk = conn.recv(1024)
            if not chunk:
                break
            data += chunk.decode('utf-8')

        print(data.replace('\r\n', '\n'))

        conn.sendall(b"HTTP/1.1 200 seccess\r\n\r\n")
        conn.close()

except KeyboardInterrupt:
    pass
finally:
    s.close()