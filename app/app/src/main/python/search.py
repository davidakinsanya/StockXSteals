import undetected_chromedriver as uc
from bs4 import BeautifulSoup as bs4
from flask import Flask
from flask import request
from concurrent.futures import ThreadPoolExecutor

app = Flask(__name__)

def driver():
    user_agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.5615.49 Safari/537.36"
    options = uc.ChromeOptions()
    options.add_argument('--headless=new')
    options.add_argument(f'user-agent={user_agent}')
    options.add_argument("--window-size=1920,1080")
    options.add_argument('--ignore-certificate-errors')
    options.add_argument('--allow-running-insecure-content')
    options.add_argument("--disable-extensions")
    options.add_argument("--proxy-server='direct://'")
    options.add_argument("--proxy-bypass-list=*")
    options.add_argument("--start-maximized")
    options.add_argument('--disable-gpu')
    options.add_argument('--disable-dev-shm-usage')
    options.add_argument('--no-sandbox')

    return uc.Chrome(options=options)



@app.route('/api-key', methods=['GET'])
def api_key():
    return "######################################"



@app.route('/', methods=['GET'])
def search():
    search_res = ""
    search_res2 = ""
    img_search = ""
    doc = ""
    
    try:
        with ThreadPoolExecutor(max_workers=20) as p:
            search_title = request.args.get('search')
            url = "https://stockx.com/en-gb/search?s={}".format(search_title)
            driver.get(url)
            search_map = {}
            doc = bs4(driver.page_source, 'lxml')

        with ThreadPoolExecutor(max_workers=20) as p:
            search_res = doc.find('div', {'class', 'loading css-1ouqd68'}) 
            search_res2 = search_res.find_all('div', {'class', 'css-pnc6ci'})
            img_search = search_res.find_all('div', {'class', 'css-tkc8ar'})

        with ThreadPoolExecutor(max_workers=20) as p:  
            for i in range(0, len(search_res2)):
                search_map[img_search[i].img['alt']] = [
                    search_res2[i].a['href'].replace('/en-gb/', ''),
                    img_search[i].img['src'] 
            ]
        
        return search_map
    except:
        pass
        

driver = driver()

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)


# docker build -t search .
# docker run --name search -p 5000:5000 search 


