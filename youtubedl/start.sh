#!/bin/bash
app="youtubedl"
docker build -t ${app} .
docker run -d ${app}
