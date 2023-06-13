import socket,os,struct
import sys

HOST = "127.0.0.1"
PORT = 8080

if len(sys.argv) < 2 :
    print('please input the file path')
    exit(1)

filePath = sys.argv[1]

if os.path.isfile(filePath):

    fileName = os.path.basename(filePath).encode('utf-8')

    commend = 1

    bodyLen = len(fileName)
    fileNameData = bytes(fileName)

    i = bodyLen.to_bytes(4,'big')
    c = commend.to_bytes(4,'big')


    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.connect((HOST, PORT))
        s.sendall(c + i + fileNameData)
        file = open(filePath,'rb')
        while True:
            commend = 2
            c = commend.to_bytes(4,'big')
            filedata = file.read(1024)
            b = len(filedata).to_bytes(4,'big')
            if not filedata:
                break
            s.sendall(c + i + fileNameData + b + filedata)
        file.close()
        s.close()

else:
    print('please input the file')




