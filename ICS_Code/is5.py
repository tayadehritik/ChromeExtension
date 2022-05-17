
# coding: utf-8

# In[23]:


import numpy as np

print("Enter a")
a=int(input())

print("Enter b")
b=int(input())

print("Enter G in x and y")
G=np.array([int(input()),int(input())])


# In[24]:


A=a*G
print("The value of A is ",A)

B=b*G
print("The value of B is ",B)



# In[25]:


key1=B*a
key2=A*b
print("The value of key1 is ",key1)
print("The value of key2 is ",key2)


# In[26]:


key=key1=key2


# In[27]:


print("Enter message in x and y format")
m=np.array([int(input()),int(input())])


# In[28]:


c1=key*G
print("The value of c1 is ",c1)

c2=m+key*B
print("The value of c2 is ",c2)


# In[29]:


decrypt1=c1*b   ##c1 and c2 are sent to receiver .He has b
finaldecrypt=c2-decrypt1

print("The value of decrypted message is ",finaldecrypt)
print("The value of original message is ",m)

