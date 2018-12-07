#!/user/bin/python

import requests
import base64
import json

url = "http://restproxy:8082/topics/myfirsttopic"
headers = {
    "Content-type" : "application/vnd.kafka.binary.v1+json"
    }

payload = { "records" :
    [{
        "key" : base64.b64encode("firstkey"),
        "value" : base64.b64encode("firstvalue")
    }]}

r = requests.post(url, data = json.dumps(payload), headers = header)

if r.status_code != 200:
    print ("Status Code: " + str(r.status_code))
    print (r.text)