import markdown2
from bs4 import BeautifulSoup


def markdown_to_slack_markup(markdown_text: str) -> str:
    html = markdown2.markdown(markdown_text)

    soup = BeautifulSoup(html, 'html.parser')

    for i in range(1, 7):
        for element in soup.find_all(f'h{i}'):
            element.replace_with(f'\n\n*✅ {element.get_text()}*\n')

    for element in soup.find_all('strong'):
        element.replace_with(f'*{element.get_text()}*')

    for element in soup.find_all('em'):
        element.replace_with(f'*{element.get_text()}*')

    for element in soup.find_all('code'):
        element.replace_with(f'`{element.get_text()}`')

    for element in soup.find_all('pre'):
        text = element.get_text()
        element.replace_with(f'```{text}```')

    for element in soup.find_all('ul', recursive=False):
        result = process_list(element)
        element.replace_with(result)

    for element in soup.find_all('ol', recursive=False):
        result = process_list(element, list_marker='1', offset=1)
        element.replace_with(result)

    for element in soup.find_all('a'):
        href = element.get('href')
        text = element.get_text()
        element.replace_with(f'<{href}|{text}>')

    for element in soup.find_all('blockquote'):
        text = element.get_text()
        element.replace_with(f'> {text}')

    return soup.get_text()


def process_list(list_tag, list_marker='•', offset=1):
    items = list_tag.find_all('li', recursive=False)
    result = ''

    for index, element in enumerate(items):
        marker = f'{index + offset}.' if list_marker.isdigit() else list_marker
        content = extract_list_item_content(element)
        result += f'\n{marker} {content}'
        result = process_nested_lists(element, html=result)

    return result


def process_nested_lists(element: BeautifulSoup, html: str) -> str:
    for child in element.find_all('ul', recursive=False):
        nested_result = process_list(child, list_marker='  •')
        html += nested_result

    for child in element.find_all('ol', recursive=False):
        nested_result = process_list(child, list_marker='  1', offset=1)
        html += nested_result

    return html


def extract_list_item_content(element: BeautifulSoup) -> str:
    content = ''
    for child in element.children:
        if not (hasattr(child, 'name') and child.name in ['ul', 'ol']):
            content += str(child)
    return BeautifulSoup(content, 'html.parser').get_text().strip()
