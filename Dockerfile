FROM p7hb/docker-spark

ENV PATH $PATH:/spark/bin/  

COPY . /app

WORKDIR /app

COPY /archive /archive
    
# RUN wget -nv https://dumps.wikimedia.org/enwiki/latest/enwiki-latest-abstract.xml.gz \
#     -O archive/enwiki-latest-abstract.tar.gz
# 
# RUN gunzip archive/enwiki-latest-abstract.tar.gz

CMD spark-submit --class "org.koshy.cigin.MovieRunner" --master local src/main/docker/deploy/true-layer-challenge-assembly-1.0.jar