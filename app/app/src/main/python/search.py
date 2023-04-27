from bs4 import BeautifulSoup as bs4
import cloudscraper


def stockx_search(search_title):
    url = "https://stockx.com/en-gb/search?s={}".format(search_title)
    search_map = {}

    scraper = cloudscraper.create_scraper(
        browser={
        'browser': 'chrome',
        'platform': 'android',
        'desktop': False
        }
    )
    doc = bs4(scraper.get(url).text, 'html.parser')
        
    search_res = doc.find('div', {'class', 'loading css-1ouqd68'})
    search_res2 = search_res.find_all('div', {'class', 'css-pnc6ci'})
    img_search = search_res.find_all('div', {'class', 'css-tkc8ar'})

    
    for i in range(0, len(search_res2)):
        search_map[img_search[i].img['alt']] = [
            search_res2[i].a['href'].replace('/en-gb/', ''),
            img_search[i].img['src'] 
         ]
        
    return search_map
   
