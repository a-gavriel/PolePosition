
import cv2

lista = []

def getpos( pix ):
    global lista
    a = 0
    largo = len(lista)
    while (a < largo):
        if (pix == (lista[a])):
            return a
        a+=1
    lista.append(pix)
    return a  

def funcion():
    image = cv2.imread("mapa.png")
    arch_contc = open ("map.txt","r+")
    filecontent = ""
    global lista
    
    for i in range(56):
        for j in range(56):
            pix = image[i,j]
            pixel = [pix[0],pix[1],pix[2]]
            num = getpos(pixel)
            filecontent += str(num)
        filecontent += "\n"
    arch_contc.write(filecontent)
    arch_contc.close()
        

funcion()

