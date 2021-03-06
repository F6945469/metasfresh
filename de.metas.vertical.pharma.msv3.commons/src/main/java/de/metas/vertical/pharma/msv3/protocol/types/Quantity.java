package de.metas.vertical.pharma.msv3.protocol.types;

import lombok.Value;

/*
 * #%L
 * metasfresh-pharma.msv3.server
 * %%
 * Copyright (C) 2018 metas GmbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

@Value
public class Quantity
{
	public static Quantity of(final int value)
	{
		if (value == 0)
		{
			return ZERO;
		}
		return new Quantity(value);
	}

	public static final Quantity ZERO = new Quantity(0);

	private static final int MAX_VALUE = 99999;

	private final int valueAsInt;

	private Quantity(final int value)
	{
		if (value < 0)
		{
			throw new IllegalArgumentException("Quantity shall be greater than " + value);
		}
		if (value > MAX_VALUE)
		{
			throw new IllegalArgumentException("The MSV3 standard allows a maximum quantity of " + value);
		}

		valueAsInt = value;
	}

	public Quantity min(final Quantity otherQty)
	{
		return valueAsInt <= otherQty.valueAsInt ? this : otherQty;
	}

	public Quantity min(final int otherQty)
	{
		return valueAsInt <= otherQty ? this : Quantity.of(otherQty);
	}
}
