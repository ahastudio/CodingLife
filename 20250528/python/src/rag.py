import os
from time import perf_counter

from llama_index.core import (
    SimpleDirectoryReader,
    StorageContext,
    VectorStoreIndex,
)
from llama_index.core.node_parser import SentenceSplitter
from llama_index.embeddings.openai import OpenAIEmbedding
from llama_index.llms.gemini import Gemini
from llama_index.llms.openai import OpenAI
from llama_index.vector_stores.postgres import PGVectorStore

# -----------------------------------------------------------------------------

embed_model = OpenAIEmbedding(
    model='text-embedding-3-small',
    api_key=os.getenv('OPENAI_API_KEY', ''),
)

llm = OpenAI(
    model='gpt-4.1-nano',
    api_key=os.getenv('OPENAI_API_KEY', ''),
)

# llm = Gemini(
#     model='gemini-2.5-flash-preview-05-20',
#     api_key=os.getenv('GEMINI_API_KEY', ''),
# )

# -----------------------------------------------------------------------------

print('=' * 80)
print('🚀 Creating vector store index...')

vector_store = PGVectorStore.from_params(
    host=os.getenv('DB_HOST', 'localhost'),
    port=os.getenv('DB_PORT', '5432'),
    database=os.getenv('DB_NAME', 'postgres'),
    user=os.getenv('DB_USER', 'postgres'),
    password=os.getenv('DB_PASSWORD', 'postgres'),
    table_name=os.getenv('DB_TABLE', 'documents'),
    embed_dim=1536,
    hnsw_kwargs={
        'hnsw_m': 16,
        'hnsw_ef_construction': 64,
        'hnsw_ef_search': 40,
        'hnsw_dist_method': 'vector_cosine_ops',
    },
)

storage_context = StorageContext.from_defaults(vector_store=vector_store)

vector_store_index = VectorStoreIndex(
    nodes=[],
    storage_context=storage_context,
    embed_model=embed_model,
    show_progress=True,
)

print('⭐️ Vector store index created successfully.')
print('=' * 80)

# -----------------------------------------------------------------------------


def load_documents():
    print('🚀 Loading documents into vector store index...')

    reader = SimpleDirectoryReader(input_dir='data', required_exts=['.pdf'])
    documents = reader.load_data()

    parser = SentenceSplitter(chunk_size=512, chunk_overlap=64)
    nodes = parser.get_nodes_from_documents(documents)

    # print('-' * 80)
    # for document in documents:
    #     print(f'Loaded document: {document.metadata}')
    # print('-' * 80)

    print(f'▶️ Loaded {len(documents)} documents.')

    # print('-' * 80)
    # for node in nodes:
    #     print(f'Loaded node: {node.metadata}')
    # print('-' * 80)

    print(f'▶️ Loaded {len(nodes)} nodes from documents.')

    vector_store_index.insert_nodes(nodes)

    print('⭐️ Documents loaded into vector store index successfully.')


# load_documents()

# -----------------------------------------------------------------------------


def ask(question: str) -> str:
    query_engine = vector_store_index.as_query_engine(llm=llm)

    print('🔍 Querying...')

    start_time = perf_counter()

    response = query_engine.query(
        question
        + '\n\n'
        + '답변은 한국어로, 1000자 이내로 해 주세요.'
        + '답변이 여러 개로 나온다면 리스트로 정리해 주세요.'
    )

    end_time = perf_counter()

    elapsed_time = end_time - start_time
    print(f'⌛️ Processing time: {elapsed_time:.6f} seconds')

    return str(response)
