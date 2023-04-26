from bs4 import BeautifulSoup as bs4
import requests
import re



def stockx_search(search_title):
    search_map = {}
    
    headers = {"User-Agent": "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.5615.138 Safari/537.3"}
    reg_url = "https://stockx.com/en-gb/search?s={}".format(search_title)

    res = requests.get(reg_url, headers=headers).text

    doc = bs4(res, 'html.parser')
        
    search_res = doc.find('div', {'class', 'loading css-1ouqd68'})
    search_res2 = search_res.find_all('div', {'class', 'css-pnc6ci'})
    img_search = search_res.find_all('div', {'class', 'css-tkc8ar'})


    for i in range(0, len(search_res2)):
        search_map[img_search[i].img['alt']] = [
            search_res2[i].a['href'],
            img_search[i].img['src']
         ] 
        
    return search_map


