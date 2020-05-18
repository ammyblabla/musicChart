import youtube_dl
from flask import Flask, request
from flask_restful import Resource, Api
app = Flask(__name__)
api = Api(app)


class GetMusicName(Resource):
    def get(self, video_id):
        url = 'https://www.youtube.com/watch?v=' + video_id
        ydl = youtube_dl.YoutubeDL({})
        with ydl:
            video = ydl.extract_info(url, download=False)
        result = {
            "artist": video['artist'],
            "track": video['track']
        }
        return result


api.add_resource(GetMusicName, '/youtube/<video_id>')

if __name__ == '__main__':
    app.run(port='5002')
