import mysql.connector
import random
import string
import csv
from datetime import datetime, timedelta

# Define the database connection parameters
db_config = {
    'host': 'localhost',
    'user': 'root',
    'password': 'sriki2001',
    'database': 'bonds',
    'auth_plugin' : 'mysql_native_password'
}

securities_len = 0
book_len = 0
user_len = 0
counterparty_len = 0

# Connect to the MySQL database
connection = mysql.connector.connect(**db_config)
cursor = connection.cursor()

# Function to generate random strings
def random_string(length):
    letters = string.ascii_letters
    return ''.join(random.choice(letters) for i in range(length))


def generate_random_alphanumeric(length):
    characters = string.ascii_letters + string.digits
    return ''.join(random.choice(characters) for _ in range(length))


def random_datetime_generator(start_date, end_date):
    # Calculate the total number of days between start_date and end_date
    total_days = (end_date - start_date).days

    # Generate a random number of days to add to the start_date
    random_days = random.randrange(total_days + 1)

    # Calculate the random datetime
    random_datetime = start_date + timedelta(days=random_days)

    return random_datetime


# Insert dummy data into User table
for i in range(1, 11):
    user_len += 1
    name = random_string(8)
    email = f'{name}@team.com'
    role = random_string(4)
    insert_query = f"INSERT INTO user (user_name, user_email, user_role) VALUES ('{name}', '{email}', '{role}')"
    cursor.execute(insert_query)
    connection.commit()

# Insert dummy data into Counterparty table
for i in range(1, 11):
    counterparty_len += 1
    name = random_string(8)
    insert_query = f"INSERT INTO counterparty (counterparty_name) VALUES ('{name}')"
    cursor.execute(insert_query)
    connection.commit()

# Insert dummy data into Book table
for i in range(1, 11):
    book_len += 1
    name = random_string(8)
    insert_query = f"INSERT INTO book (book_name) VALUES ('{name}')"
    cursor.execute(insert_query)
    connection.commit()


# Insert dummy data into Securities table
with open('/home/sriki2001/Downloads/securities_data.csv', mode='r') as csv_file: # path to the CSV file
    #read csv using reader class
    csv_reader = csv.reader(csv_file)
    #skip header
    header = next(csv_reader)
    #Read csv row wise and insert into table
    for row in csv_reader:
        securities_len += 1
        cusip = generate_random_alphanumeric(9)
        isin = generate_random_alphanumeric(12)
        status = random.choice(['redeemed', 'matured', 'active'])
        row.extend([cusip, isin, status])
        row = row[1:]
        date_object = datetime.strptime(row[4], '%d-%b-%y')
        row[4] = date_object.strftime('%Y-%m-%d')
        sql = "INSERT INTO security (issuer, security_type, coupon, face_value, maturity_date, cusip, isin, status) VALUES (%s, %s, %s, %s, %s, %s, %s, %s)"
        cursor.execute(sql, tuple(row))
 
    connection.commit()


# Insert dummy data into book_user table
# for i in range(1, 6):
#     book_id = random.randint(1, book_len)
#     user_id = random.randint(1, user_len)
#     row = [book_id, user_id]
#     sql = "INSERT INTO book_user (book_id, user_id) VALUES ('%s', '%s')"
#     cursor.execute(sql, tuple(row))

# Insert dummy data into trade table
for i in range(1, 20):
    buy_sell = random.choice(['buy', 'sell'])
    price = random.randint(200, 2001)
    quantity = random.randint(1, 10)
    security_id = random.randint(10, securities_len*5)
    book_id = random.randint(1, book_len)
    counterparty_id = random.randint(1, counterparty_len)


    condition_value = security_id

    # SQL query to fetch the record
    sql = "SELECT * FROM security WHERE id = %s"

    # Execute the query with the condition value as a parameter
    cursor.execute(sql, (condition_value,))

    # Fetch the record (if exists)
    record = cursor.fetchone()

    if (record is None):
        continue

    # record[6] corresponds to maturity date
    trade_date = datetime.today() if (buy_sell == "buy") else random_datetime_generator(datetime.today(), datetime.combine(record[6], datetime.min.time()))
    settlement_date = trade_date + timedelta(days=2)
    trade_date = trade_date.strftime("%Y-%m-%d")
    settlement_date = settlement_date.strftime("%Y-%m-%d")

    sql = "INSERT INTO trade (buy_or_sell, price, quantity, settlement_date, status, trade_date, book_id, counterparty_id, security_id) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)"

    cursor.execute(sql, (buy_sell, price, quantity, settlement_date, record[-1], trade_date, book_id, counterparty_id, security_id))
    
    connection.commit()



print("Script completed successfully")

# Close the database connection
cursor.close()
connection.close()
