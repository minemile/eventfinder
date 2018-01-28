from requests import request
import re
from datetime import datetime

with open('serviceToken','r') as f:
    TOKEN = f.read()

# Powerhouse id present
query = 'https://api.vk.com/method/wall.get?owner_id=' + '-62811864' + '' +'&access_token=' + TOKEN

wall = request("POST", query).json()['response']

for post in wall[1:]:
    text = post['text']
    if any([w for w in ["вход", "свободн", "бесплатн", "рублей"] if w in text.lower()]):
        res = {}
        res['desc'] = text
        if '[club' in text:
            comm_id = re.search('\[club(.*)\|', text).group(1)
            event_query = "https://api.vk.com/method/groups.getById?group_ids= " \
                    + comm_id + "&fields=place,start_date&access_token=" + TOKEN
            comm_info = request("POST", event_query).json()

            res["name"] = comm_info['response'][0]['name']
            date_timestamp = int(comm_info['response'][0]['start_date'])
            res['date'] = datetime.fromtimestamp(date_timestamp).isoformat()
            # stub
            res['place'] = "ПАВЕРХАУС"
            res['price'] = "БЕСПЛАТНО"
        print(res)

        # actions with database

