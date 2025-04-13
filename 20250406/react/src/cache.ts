import { createClient } from 'redis';

const TTL_SECONDS = 60;

const client = createClient({
  url: 'redis://localhost:6379',
});

client.connect();

export async function readCache<T>(keys: string | string[]): Promise<T | null> {
  const key = Array.isArray(keys) ? keys.join(':') : keys;
  const value = await client.get(key);
  if (!value) {
    return null;
  }
  return JSON.parse(value);
}

export async function writeCache<T>(
  keys: string | string[],
  value: T,
): Promise<void> {
  const key = Array.isArray(keys) ? keys.join(':') : keys;
  await client.set(key, JSON.stringify(value));
  await client.expire(key, TTL_SECONDS);
}

export async function invalidateCache(keys: string | string[]): Promise<void> {
  const key = Array.isArray(keys) ? keys.join(':') : keys;
  await client.del(key);
}
