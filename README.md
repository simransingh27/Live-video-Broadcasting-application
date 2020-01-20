# Live-video-Broadcasting-application

The fully implemented Live video-streaming solution has a broadcasting application and an end-user application.
This is a Broadcasting application.
Functionality : 
it allows the user to broadcast their content to a set of users. 
In this solution it is merged with the Kafka producer application which first breaks up a video into frames and then publishes 
those frames to a Kafka Topic. 
This application basically makes up the data acquisition layer
i.e. collecting and transforming the stream of video and broadcasting the same stream on a real-time basis.
