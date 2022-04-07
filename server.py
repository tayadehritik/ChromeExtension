import asyncio
import pathlib
import ssl
import websockets
import re
async def hello(websocket, path):
    url = ""
    while True:
        name = await websocket.recv()
 
        if(f"{name}"[0] == "p"):
            url = f"{name}"[1:]
            await websocket.send("pong")
        else:
            
            exp = re.compile("(.{0,})\[\](.{0,})")
            match = exp.match(f"{name}"+" [] "+url)
            ulocater = match.group(2)
            tag = match.group(1)
            f = open('chlog.txt', 'ab+')
            buff = tag + ulocater +"\n"
            f.write(buff.encode())
            f.close()
               
            print(tag,ulocater)

#ssl_context = ssl.SSLContext(ssl.PROTOCOL_TLS_SERVER)
#ssl_context.load_cert_chain(
#    pathlib.Path(__file__).with_name('cert.pem'))

start_server = websockets.serve(
    hello, '0.0.0.0', 8765)

asyncio.get_event_loop().run_until_complete(start_server)
asyncio.get_event_loop().run_forever()
