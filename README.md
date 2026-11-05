# Peer2Peer_File_Transfer

In this assignment, I worked with a partner to build a peer-to-peer file transfer system using Java TCP sockets. I developed the receiver application, and my partner developed the sender application.

The goal was to apply key networking concepts covered in class, including IP addressing, ports, TCP communication, firewalls, client/server architecture, and network troubleshooting.

Both parts of the program integrated well. The only architectural adjustment we made was to the file-naming functionality: rather than transmitting metadata headers, we aligned our protocol to stream raw payload bytes directly into a predefined destination file (received_test.txt).

Before transferring the file, we configured our VirtualBox network settings to use a Bridged Adapter so each VM could obtain an IP on the local subnet, and I opened port 5000 in the Ubuntu firewall (ufw) to accept incoming TCP connections.

While testing on the college Wi-Fi network, we encountered client-isolation restrictions that blocked direct peer-to-peer traffic between our machines. To resolve this, we connected both host machines to a personal smartphone hotspot on the same subnet. Once connected, the TCP handshake succeeded, the stream transferred smoothly, and the file contents were verified on the receiver.
