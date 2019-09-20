# TS3-Viewer
This tool enables you to see hidden/invisible clients on teamspeak servers in unsubscribed channels.

!!You have to see a client once before it can be found by the tool!!
This is because the clients uid has to be saved (by the tool) in the user db first.

# Usage
First you have to install the client query plugin in your teamspeak client.
On first startup the tool creates a configuration file _conf.properties_.

1. You have to open it with a text editor an enter your clients ip address (if you are starting the tool on the same computer as your client use 127.0.0.1)
2. You have to enter the port which should be 25639.
3. You have to enter the api-key which you can find in the settings of the client-query plugin

After this you can start the tool again after you connected to a teamspeak server in your client.

# Important
This program may not work perfectly but in my test it did most of the time.
iF you run into an error or see weird user names please try delete the user.db

*Important: the porgram refreshes automatically every 45secs this delay is because of the flood limitations of teamspeak servers. If you get kicked by a server make sure there are not to many entrys in your user db and if you are joining on a big server make sure to set learn to 0 in your config. Be aware that in this case the tool will not add new clients to its database and you will only see tha once you already added.*
