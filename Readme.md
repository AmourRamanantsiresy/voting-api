# Voting api

You can use this API to create an voting app.

# Good to know

The admin will create a `vote` that will be wrap a list of vote section.
`Vote Section` will contains the list of all candidate to the concern section.

# Steps for admin

1. Create admin user:

   | **Method** | **Path**       | **Security** |
   | ---------- | -------------- | ------------ |
   | **POST**   | `/auth/signup` | `false`      |

   - **Body**

   ```json
   {
     "username": "john",
     "password": "12345678"
   }
   ```

2. Get token

   | **Method** | **Path**       | **Security** |
   | ---------- | -------------- | ------------ |
   | **POST**   | `/auth/signin` | `false`      |

   - **Body**

   ```json
   {
     "username": "john",
     "password": "12345678"
   }
   ```

3. Create vote

   | **Method** | **Path** | **Security** |
   | ---------- | -------- | ------------ |
   | **PUT**    | `/vote`  | `true`       |

   - **Body**

   ```json
   {
     "name": "VLF",
     "votersCountAllowed": 100
   }
   ```

4. Create vote section

   | **Method** | **Path**                     | **Security** |
   | ---------- | ---------------------------- | ------------ |
   | **PUT**    | `/vote/{voteId}/voteSection` | `true`       |

   - **Body**

   ```json
   {
     "name": "President",
     "voteCountAllowed": 1
   }
   ```

5. Create vote candidate

   | **Method** | **Path**                                 | **Security** |
   | ---------- | ---------------------------------------- | ------------ |
   | **PUT**    | `/voteSection/{voteSectionId}/candidate` | `true`       |

   - **Body**

   ```json
   [
     {
       "name": "Dan"
     },
     {
       "name": "Ben"
     }
   ]
   ```

# Steps for voters

1. Get vote list

   | **Method** | **Path** | **Security** |
   | ---------- | -------- | ------------ |
   | **GET**    | `/vote`  | `false`      |

2. Get vote section list

   | **Method** | **Path**                     | **Security** |
   | ---------- | ---------------------------- | ------------ |
   | **GET**    | `/vote/{voteId}/voteSection` | `false`      |

3. Get vote candidate list

   | **Method** | **Path**                                 | **Security** |
   | ---------- | ---------------------------------------- | ------------ |
   | **GET**    | `/voteSection/{voteSectionId}/candidate` | `false`      |

4. Vote

   | **Method** | **Path**              | **Security** |
   | ---------- | --------------------- | ------------ |
   | **PUT**    | `/vote/{voteId}/make` | `false`      |

   - **Body**

   ```json
   [
     {
       "sectionId": "7aca88de-6ea9-4748-95c8-a8533f8ecd81",
       "candidateIds": ["19e533d9-a373-40b1-80d8-e47663f621c6"]
     }
   ]
   ```
