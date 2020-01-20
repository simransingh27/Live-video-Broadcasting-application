# Live-video-Broadcasting-application
![Video Streaming](https://user-images.githubusercontent.com/40739676/72758958-306c6a00-3bcc-11ea-84a8-1795806d4d08.PNG)

Video streaming is a type of media streaming where the user receives video chunks while being delivered by a source through a server in a real-time fashion. A live-video means to view a video online across the world without necessarily being stored on a device.

![Overview](https://user-images.githubusercontent.com/40739676/72759171-c6a09000-3bcc-11ea-9a99-1ffe9821f2c5.PNG)

The final solution fully supports Live video-streaming, on-demand video streaming and implement straightforward analytics 
The solution has spread across multiple projects : 
This project is a broadcasting application build using JavaFX. Also this application basically makes up the data acquisition layer.
Functionality : 
It allows the user to broadcast their content to a set of users. OpenCV is used to fetch live images and it is merged with the Kafka producer application which first breaks up a video into frames and then publishes them to a Kafka Topic. 

Below the Screenshot of Publisher app with Kafka Producer instance interacts with Kafka broker.


![BroadcastingApp](https://user-images.githubusercontent.com/40739676/72759498-cce33c00-3bcd-11ea-8f2b-f59b54576ee5.PNG)

