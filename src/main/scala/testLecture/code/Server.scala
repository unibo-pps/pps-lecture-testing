package testLecture.code

object Server:
  case class Request(body: String)
  case class Response(content: String)

  class Server:
    def processRequest(req: Request): Response = Response(req.body + "'s Response")
    def serve(req: Request): Response = processRequest(req)

  trait Cache[K, T]:  // Note: we provide no implementation class yet
    def cached(key: K): Boolean
    def get(key: K): T
    def put(key: K, value: T): Unit

  class ServerWithCache(private val cache: Cache[Request, Response],
                        private val cacheable: Request => Boolean) extends Server:
    override def serve(req: Request) =
      if (!cacheable(req)) {
        super.serve(req)
      } else {
        if (cache.cached(req)) {
          cache.get(req)
        } else {
          val res = processRequest(req)
          cache.put(req, res)
          res
        }
      }