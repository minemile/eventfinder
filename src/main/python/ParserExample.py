from requests import request
import re
from datetime import datetime
import psycopg2

with open('serviceToken','r') as f:
    TOKEN = f.read()

cnx = psycopg2.connect("dbname='eventfinder' user='postgres' host='localhost' password='1'")
cnx.autocommit = True
cur = cnx.cursor()


# Powerhouse id present
query = 'https://api.vk.com/method/wall.get?owner_id=' + '-62811864' + '' +'&access_token=' + TOKEN

wall = request("POST", query).json()['response']

for post in wall[1:]:
    text = post['text']
    if any([w for w in ["свободн", "бесплатн"] if w in text.lower()]):
        party = {}
        party['description'] = text
        if '[club' in text:
            comm_id = re.search('\[club(.*)\|', text).group(1)
            event_query = "https://api.vk.com/method/groups.getById?group_ids= " \
                    + comm_id + "&fields=place,start_date,screen_name&access_token=" + TOKEN
            comm_info = request("POST", event_query).json()['response'][0]


            party["name"] = comm_info['name']
            date_timestamp = int(comm_info['start_date'])
            party['date'] = str(datetime.fromtimestamp(date_timestamp).isoformat())
            # stub
            party['place'] = "Powerhouse"
            party['price'] = 0
            party['link'] = 'https://vk.com' + comm_info['screen_name']

            try:
                cur.execute("INSERT INTO tusovka (name, description, date, price, link, place) VALUES (%s, %s, %s, %s, %s, %s)",
                            [party[n] for n in ['name', 'description', 'date', 'price', 'link', 'place']])
            except psycopg2.DatabaseError as e:
                if cnx:
                    cnx.rollback()
cur.close()
cnx.close()
