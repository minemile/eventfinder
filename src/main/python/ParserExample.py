from requests import request
import re
from datetime import datetime
import mysql.connector

with open('serviceToken','r') as f:
    TOKEN = f.read()

cnx = mysql.connector.connect(user='root', password='1',
                              host='127.0.0.1',
                              database='eventfinder')
cursor = cnx.cursor()


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
                    + comm_id + "&fields=place,start_date&access_token=" + TOKEN
            comm_info = request("POST", event_query).json()

            party["name"] = comm_info['response'][0]['name']
            date_timestamp = int(comm_info['response'][0]['start_date'])
            party['date'] = str(datetime.fromtimestamp(date_timestamp).isoformat())
            # stub
            party['place'] = "ПАВЕРХАУС"
            party['price'] = 0

            query = "INSERT INTO Tusovka (name, date, description, price, place_id) VALUES " \
                    "(%(name)s, %(date)s, %(description)s, %(price)s, " \
                    "(SELECT id FROM Place WHERE Place.name = 'Powerhouse'))"
            cursor.execute(query, party)

            cnx.commit()

cursor.close()
cnx.close()
