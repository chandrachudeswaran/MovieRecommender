import requests
import argparse

parser = argparse.ArgumentParser()
parser.add_argument("moviename", help="Enter a movie name")
args = parser.parse_args()


CONFIG_PATTERN = 'http://api.themoviedb.org/3/configuration?api_key={key}'
KEY = '22906c48297d9c93ef26085d61b44411'

import requests
import urllib

def imdb_id_from_title(title):
    """ return IMDB id for search string

        Args::
            title (str): the movie title search string

        Returns: 
            str. IMDB id, e.g., 'tt0095016' 
            None. If no match was found

    """
    pattern = 'http://www.imdb.com/xml/find?json=1&nr=1&tt=on&q={movie_title}'
    url = pattern.format(movie_title=urllib.quote(title))
    r = requests.get(url)
    res = r.json()
    # sections in descending order or preference
    for section in ['popular','exact','substring']:
        key = 'title_' + section 
        if key in res:
            return res[key][0]['id']
			
			
url = CONFIG_PATTERN.format(key=KEY)

m = args.moviename
movieID = imdb_id_from_title(m)
r = requests.get(url)
config = r.json()

base_url = config['images']['base_url']
sizes = config['images']['poster_sizes']
"""
    'sizes' should be sorted in ascending order, so
        max_size = sizes[-1]
    should get the largest size as well.        
"""
def size_str_to_int(x):
    return float("inf") if x == 'original' else int(x[1:])
max_size = max(sizes, key=size_str_to_int)

IMG_PATTERN = 'http://api.themoviedb.org/3/movie/{imdbid}/images?api_key={key}' 
r = requests.get(IMG_PATTERN.format(key=KEY,imdbid=movieID))
api_response = r.json()

posters = api_response['posters']
poster_urls = []
count = 0
for poster in posters:
    rel_path = poster['file_path']
    url = "{0}{1}{2}".format(base_url, max_size, rel_path)
    if count < 1:
       poster_urls.append(url)
       print url
       count = count + 1
	
	
'''for nr, url in enumerate(poster_urls):
    r = requests.get(url)
    filetype = r.headers['content-type'].split('/')[-1]
    filename = 'poster_{0}.{1}'.format(nr+1,filetype) 
    with open(filename,'wb') as w:
        w.write(r.content)'''