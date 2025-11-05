const CACHE_NAME = 'fraud-alert-v1';
const STATIC_ASSETS = [
    '/',
    '/manifest.json',
    '/icons/icon-72.png',
    '/icons/icon-96.png',
    '/icons/icon-128.png',
    '/icons/icon-192.png',
    '/icons/icon-256.png',
    '/icons/icon-384.png',
    '/icons/icon-512.png',
    '/index.html'           // your main inline HTML
];

// ✅ Install event – cache assets
self.addEventListener('install', (evt) => {
    evt.waitUntil(
        caches.open(CACHE_NAME).then(cache => {
            console.log('[ServiceWorker] Caching static assets');
            return cache.addAll(STATIC_ASSETS);
        })
    );
    self.skipWaiting();
});

// ✅ Activate event – clean old caches
self.addEventListener('activate', (evt) => {
    evt.waitUntil(
        caches.keys().then(keys =>
            Promise.all(keys.filter(k => k !== CACHE_NAME).map(k => caches.delete(k)))
        )
    );
    self.clients.claim();
});

// ✅ Fetch event – serve from cache first
self.addEventListener('fetch', (evt) => {
    evt.respondWith(
        caches.match(evt.request).then(cached => cached || fetch(evt.request))
    );
});
