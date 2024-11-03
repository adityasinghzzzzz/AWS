exports.handler = async (event) => {
  // Extract the path and HTTP method from the event object
  const { path, httpMethod } = event;

  // Define the response structure
  let response;

  // Check if the path is /hello and the method is GET
  if (path === "/hello" && httpMethod === "GET") {
    response = {
      statusCode: 200,
      message: "Hello from Lambda",
    };
  } else {
    // Return a 400 Bad Request for any other path or method
    response = {
      statusCode: 400,
      message: `Bad request syntax or unsupported method. Request path: ${path}. HTTP method: ${httpMethod}`,
    };
  }

  return response;
};
