
# coding: utf-8

# In[26]:


print("Enter 10 bit key")   ## intial key
key=input()


# In[27]:


print("Enter 8 bit text")
text=input()


# In[28]:


p10=[3,5,2,7,4,10,1,9,8,6]
p8=[6,3,7,4,8,5,10,9]
p4=[2,4,3,1]
ip=[2,6,3,1,4,8,5,7]
iip=[4,1,3,5,7,2,8,6]
e=[4,1,2,3,2,3,4,1]
s0=[
    [1,0,3,2],
    [3,2,1,0],
    [0,2,1,3],
    [3,1,3,2]
]
s1=[
        [0, 1, 2, 3],
        [2, 0, 1, 3],
        [3, 0, 1, 0],
        [2, 1, 0, 3]
]


# In[29]:


def permute(p,l):
    t=""
    for i in p:
        t=t+l[i-1]
        
    return t
    


# In[30]:


def firstkey(left,right):
    temp1=left[1:]
    temp2=right[1:]
    temp1=temp1+left[:1]
    temp2=temp2+right[:1]
    return permute(p8,temp1+temp2)
    
    


# In[31]:


def secondkey(left,right):
    temp1=left[2:]
    temp2=right[2:]
    temp1=temp1+left[:2]
    temp2=temp2+right[:2]
    return permute(p8,temp1+temp2)


# In[32]:


def f(left,right,key):
    temp=permute(e,right)
    temp=bin(int(temp,2)^int(key,2))[2:].zfill(8)
    temp1=temp[0:4]
    temp2=temp[4:]
    temp1=ss(temp1,s0)
    temp2=ss(temp2,s1)
    temp=temp1+temp2
    fo=permute(p4,temp)
    fo=bin(int(fo,2)^int(left,2))[2:].zfill(4)
    return fo,right
    
    


# In[33]:


def ss(input1, sbox):
    row = int(input1[0] + input1[3], 2)
    column = int(input1[1] + input1[2], 2)
    return bin(sbox[row][column])[2:].zfill(4)


# In[34]:


permute10=permute(p10,key)
print(permute10)  ##intial p10


# In[35]:


left=permute10[0:5]
right=permute10[5:]
print(left,right)   ##left right of p10


# In[36]:


k1=firstkey(left,right)   ##round1 key
print("The first round key is k1 ",k1)


# In[37]:


k2=secondkey(left,right)  ##round 2 key
print("The second round key is k2 ",k2)


# In[38]:


textpermute=permute(ip,text)
print("The initial text permute is ",textpermute)


# In[39]:


lefttext,righttext=textpermute[0:4],textpermute[4:]
print(lefttext,righttext)


# In[40]:


left,right=f(lefttext,righttext,k1)   ##first round
print(left,right)


# In[41]:


left,right=f(right,left,k2)  ##second round
print(left,right)


# In[42]:


c=permute(iip,left+right)
print("The cipher text is ",c)


# In[18]:


###for decryption pass k2 for first round then k1 and input is above c cipher text for decryption

