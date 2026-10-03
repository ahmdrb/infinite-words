const VERSION = 'v5';
const SHELL_CACHE = `wordmos-shell-${VERSION}`;
const RUNTIME_CACHE = `wordmos-runtime-${VERSION}`;
const APP_FILE = 'wordmos_v5.html';
const APP_URL = new URL(`./${APP_FILE}`, self.registration.scope).href;

async function cacheIfAvailable(cache, url) {
  try {
    const response = await fetch(url, { cache: 'no-store' });
    if (response.ok) await cache.put(url, response.clone());
  } catch (_) {}
}

self.addEventListener('install', event => {
  event.waitUntil((async () => {
    const shell = await caches.open(SHELL_CACHE);
    await cacheIfAvailable(shell, APP_URL);
    await cacheIfAvailable(shell, new URL('./manifest.json', self.registration.scope).href);
    await cacheIfAvailable(shell, new URL('./icon.svg', self.registration.scope).href);
    self.skipWaiting();
  })());
});

self.addEventListener('activate', event => {
  event.waitUntil((async () => {
    const keys = await caches.keys();
    await Promise.all(keys
      .filter(key => key.startsWith('wordmos-') && key !== SHELL_CACHE && key !== RUNTIME_CACHE)
      .map(key => caches.delete(key)));
    await self.clients.claim();
  })());
});

self.addEventListener('message', event => {
  const data = event.data || {};
  if (data.type === 'SKIP_WAITING') self.skipWaiting();
  if (data.type === 'CACHE_APP_SHELL' && typeof data.url === 'string') {
    event.waitUntil((async () => {
      try {
        const response = await fetch(data.url, { cache: 'no-store' });
        if (response.ok) {
          const cache = await caches.open(RUNTIME_CACHE);
          await cache.put(data.url, response.clone());
        }
      } catch (_) {}
    })());
  }
});

async function networkFirst(request) {
  const timeout = new Promise((_, reject) => setTimeout(() => reject(new Error('network timeout')), 4500));
  try {
    const response = await Promise.race([fetch(request), timeout]);
    if (response && response.ok) {
      const cache = await caches.open(RUNTIME_CACHE);
      await cache.put(request, response.clone());
    }
    return response;
  } catch (_) {
    const cached = await caches.match(request);
    if (cached) return cached;
    const shell = await caches.match(APP_URL);
    if (shell) return shell;
    throw _;
  }
}

async function cacheFirst(request) {
  const cached = await caches.match(request);
  if (cached) return cached;
  try {
    const response = await fetch(request);
    if (response && response.ok) {
      const cache = await caches.open(RUNTIME_CACHE);
      await cache.put(request, response.clone());
    }
    return response;
  } catch (error) {
    const shell = await caches.match(APP_URL);
    if (shell && request.mode === 'navigate') return shell;
    throw error;
  }
}

self.addEventListener('fetch', event => {
  const request = event.request;
  if (request.method !== 'GET') return;
  const url = new URL(request.url);
  if (url.origin !== self.location.origin) return;
  if (url.pathname.endsWith('/wordmos-sw.js')) return;

  if (request.mode === 'navigate') {
    event.respondWith(networkFirst(request));
  } else {
    event.respondWith(cacheFirst(request));
  }
});
